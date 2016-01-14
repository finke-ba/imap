package com.imap.services.impl;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import com.imap.domain.BoilerRegion;
import com.imap.domain.ControlObject;
import com.imap.services.BoilerMapService;
import com.imap.services.validation.BoilerValidationService;
import com.imap.services.validation.RegionValidationService;
import com.imap.services.validation.TownValidationService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для получения информации о всех приборах учета на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerMapServiceImpl implements BoilerMapService {

	/** Соотношение идентиикатора города к котельным в этом городе.(<TownId <BoilerId, Boiler>>) */
	private HashMap<Integer, HashMap<Integer, Boiler>> townMap = new HashMap<>();

	/** Соотношение идентификатора котельной к идентификатору города.(<BoilerId, TownId>) */
	private HashMap<Integer, Integer> boilerTownMap = new HashMap<>();

	/** Соотношение идентификатора котельной к списку с проверенными данными котельной.(<BoilerId, List<BoilerUIVO>>)*/
	private Map<Integer, List<BoilerUIVO>> boilerMapChecked;

	/** Соотношение идентификатора города к списку с проверенными данными котельных в городе.(<TownId, List<TownUIVO>>)*/
	private Map<Integer, List<TownUIVO>> townMapChecked;

	/** Список с проверенными данными котельных в регионе.*/
	private List<RegionUIVO> regionListChecked;

	/** Период обновления данных из БД. */
	private static final long UPDATE_PERIOD = 300_000;

	@Autowired
	private BoilerDao boilerDao;

	@Autowired
	private BoilerValidationService boilerValidationService;

	@Autowired
	private TownValidationService townValidationService;

	@Autowired
	private RegionValidationService regionValidationService;

	/**
	 * Обновляет данные о котельных.
	 */
	@Scheduled(fixedDelay = UPDATE_PERIOD)
	private void update() {
		getBoilerRegion();
		validateBoilerRegion();
	}

	private void getBoilerRegion() {
		//Получили ResultSet всех котельнях в регионе
		List<BoilerRegion> boilerRegionList = boilerDao.getBoilerRegion();

		//Преобразовать ResultSet в структурированный объект
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
	}

	private void validateBoilerRegion() {

		boilerMapChecked = boilerValidationService.getBoilerMapChecked(Collections.unmodifiableMap(boilerTownMap),
				Collections.unmodifiableMap(townMap));

		townMapChecked = townValidationService.getTownMapChecked(Collections.unmodifiableMap(townMap));

		regionListChecked = regionValidationService.getRegionListChecked(Collections.unmodifiableMap(townMap));

	}

	@Override
	public Map<Integer, Map<Integer, Boiler>> getTownMap() {
		return Collections.unmodifiableMap(townMap);
	}

	@Override
	public Map<Integer, Integer> getBoilerTownMap() {
		return Collections.unmodifiableMap(boilerTownMap);
	}

	@Override
	public Map<Integer, List<BoilerUIVO>> getBoilerChecked() {
		return boilerMapChecked;
	}

	@Override
	public Map<Integer, List<TownUIVO>> getTownChecked() {
		return townMapChecked;
	}

	@Override
	public List<RegionUIVO> getRegionChecked() {
		return regionListChecked;
	}

}
