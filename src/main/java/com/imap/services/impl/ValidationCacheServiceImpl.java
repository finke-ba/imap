package com.imap.services.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import com.imap.domain.BoilerRegion;
import com.imap.domain.ControlObject;
import com.imap.exceptions.NoSuchItemException;
import com.imap.services.ValidationCacheService;
import com.imap.services.validation.BoilerValidationService;
import com.imap.services.validation.RegionValidationService;
import com.imap.services.validation.TownValidationService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Класс содержащий кэш с данными, прошедшими валидацию.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class ValidationCacheServiceImpl implements ValidationCacheService {

	/** Соотношение идентификатора котельной к идентификатору города.(<BoilerId, TownId>) */
	private HashMap<Integer, Integer> boilerTownMap = new HashMap<>();

	/** Интерфейс доступа к данным по котельным. */
	@Autowired
	private BoilerDao boilerDao;

	/** Интерфейс для проверки и получения информации об определенных котельных.*/
	@Autowired
	private BoilerValidationService boilerValidationService;

	/** Интерфей для проверки и получения информации о всех котельных в определенном городе.*/
	@Autowired
	private TownValidationService townValidationService;

	/** Интерфейс для проверки и получения информации о всех котельных во всех городах в регионе.*/
	@Autowired
	private RegionValidationService regionValidationService;

	/** Кэш с проверенными данными по котельной.*/
	private final LoadingCache<Integer, List<BoilerUIVO>> boilerCache =
			CacheBuilder.newBuilder()
					.expireAfterAccess(1, TimeUnit.MINUTES)
					.build(new CacheLoader<Integer, List<BoilerUIVO>>() {
						@Override
						public List<BoilerUIVO> load(Integer boilerId) throws Exception {
							HashMap<Integer, HashMap<Integer, Boiler>> boilerRegion = new HashMap<>(boilerRegionCache.get(0));
							Integer townId = boilerTownMap.get(boilerId);
							return boilerValidationService.getBoilerChecked(boilerRegion.get(townId).get(boilerId));
						}
					});

	/** Кэш с проверенными данными по городу.*/
	private final LoadingCache<Integer, List<TownUIVO>> townCache =
			CacheBuilder.newBuilder()
					.expireAfterAccess(1, TimeUnit.MINUTES)
					.build(new CacheLoader<Integer, List<TownUIVO>>() {
						@Override
						public List<TownUIVO> load(Integer townId) throws Exception {
							HashMap<Integer, HashMap<Integer, Boiler>> boilerRegion = new HashMap<>(boilerRegionCache.get(0));
							return townValidationService.getTownChecked(boilerRegion.get(townId));
						}
					});

	/** Кэш с проверенными данными по региону.*/
	private final LoadingCache<Integer, List<RegionUIVO>> regionCache =
			CacheBuilder.newBuilder()
					.expireAfterAccess(1, TimeUnit.MINUTES)
					.build(new CacheLoader<Integer, List<RegionUIVO>>() {
						@Override
						public List<RegionUIVO> load(Integer key) throws Exception {
							HashMap<Integer, HashMap<Integer, Boiler>> boilerRegion = new HashMap<>(boilerRegionCache.get(0));
							return regionValidationService.getRegionChecked(Collections.unmodifiableMap(boilerRegion));
						}
					});

	/** Кэш с данными по региону.*/
	private final LoadingCache<Integer, HashMap<Integer, HashMap<Integer, Boiler>>> boilerRegionCache =
			CacheBuilder.newBuilder()
					.expireAfterAccess(1, TimeUnit.MINUTES)
					.build(new CacheLoader<Integer, HashMap<Integer, HashMap<Integer, Boiler>>>() {
						@Override
						public HashMap<Integer, HashMap<Integer, Boiler>> load(Integer key) throws Exception {
							return getBoilerRegion();
						}
					});

	/**
	 * Получает данные по всем котельным в регионе.
	 *
	 * @return данные по всем котельным в регионе
	 */
	private HashMap<Integer, HashMap<Integer, Boiler>> getBoilerRegion() {
		HashMap<Integer, HashMap<Integer, Boiler>> townMap = new HashMap<>();
		List<BoilerRegion> boilerRegionList = boilerDao.getBoilerRegion();

		for (BoilerRegion boilerRegion : boilerRegionList) {
			Boiler boiler = new Boiler();
			boiler.setBoilerId(boilerRegion.getBoilerId());
			boiler.setBoilerAddress(boilerRegion.getBoilerAddress());
			boiler.setBoilerName(boilerRegion.getBoilerName());
			boiler.setTownName(boilerRegion.getTownName());

			ControlObject controlObject = new ControlObject();
			controlObject.setId(boilerRegion.getParamId());
			controlObject.setParamName(boilerRegion.getParamName());
			controlObject.setParamValue(boilerRegion.getParamValue());
			controlObject.setDate(boilerRegion.getParamDate());

			int boilerId = boilerRegion.getBoilerId();
			int townId = boilerRegion.getTownId();

			boilerTownMap.put(boilerId, townId);

			if (townMap.containsKey(boilerRegion.getTownId())) {
				HashMap<Integer, Boiler> boilerMap = townMap.get(townId);
				if (boilerMap.containsKey(boilerId)) {
					Boiler boilerAPV = boilerMap.get(boilerId);
					if (!controlObject.getId().equals(0)) {
						boilerAPV.getControlObjects().add(controlObject);
					}
				} else {
					if (!controlObject.getId().equals(0)) {
						boiler.getControlObjects().add(controlObject);
					}
					boilerMap.put(boilerId, boiler);
				}
			} else {
				HashMap<Integer, Boiler> boilerMap = new HashMap<>();
				if (!controlObject.getId().equals(0)) {
					boiler.getControlObjects().add(controlObject);
				}
				boilerMap.put(boilerId, boiler);
				townMap.put(townId, new HashMap<>(boilerMap));
			}
		}
		return townMap;
	}

	@Override
	public List<BoilerUIVO> getBoilerChecked(Integer boilerId) throws NoSuchItemException {
		try {
			return boilerCache.get(boilerId);
		} catch (ExecutionException e) {
			throw new NoSuchItemException();
		}
	}

	@Override
	public List<TownUIVO> getTownChecked(Integer townId) throws NoSuchItemException {
		try {
			return townCache.get(townId);
		} catch (ExecutionException e) {
			throw new NoSuchItemException();
		}
	}

	@Override
	public List<RegionUIVO> getRegionChecked() throws NoSuchItemException {
		try {
			return regionCache.get(0);
		} catch (ExecutionException e) {
			throw new NoSuchItemException();
		}
	}

}
