package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.services.BoilerMapService;
import com.imap.services.RegionService;
import com.imap.services.TownService;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации о всех котельных во всех городах в регионе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class RegionServiceImpl implements RegionService {

	/** Сервис для проверки данных котельных в городе. */
	@Autowired
	private TownService boilerTownService;

	/** Интерфейс доступа к данным по котельным в БД. */
	@Autowired
	private BoilerMapService boilerMapService;

	@Override
	public List<RegionUIVO> getBoilersForRegionChecked() {
		final Map<Integer, Map<Integer, Boiler>> townMap = boilerMapService.getTownMap();

		List<RegionUIVO> regionUIVOs = new ArrayList<>();
		townMap.forEach((k, v) -> regionUIVOs.add(addCheckedTown(boilerTownService.getTownChecked(v), k)));

		return regionUIVOs;
	}

	/**
	 * Преобразует список проверенных котельных в городе в объект, содержащий данные проверки города.
	 *
	 * @param townUIVOs список проверенных котельных в городе
	 * @param townId    идентификатор города
	 * @return данные о проверенном городе
	 */
	private RegionUIVO addCheckedTown(List<TownUIVO> townUIVOs, Integer townId) {
		TownUIVO townUIVO = new TownUIVO();
		townUIVO.setTownId(townId);
		return mapBoilerRegionUIVO(boilerTownService.mapCheckedTown(townUIVO, townUIVOs));
	}

	/**
	 * Преобразует данные о проверенных котельных в городе в объект, содержащий данные проверки города.
	 *
	 * @param townUIVO данные о проверенных котельных в городе
	 * @return данные о проверенном городе
	 */
	private RegionUIVO mapBoilerRegionUIVO(TownUIVO townUIVO) {
		RegionUIVO regionUIVO = new RegionUIVO();
		regionUIVO.setTownId(townUIVO.getTownId());
		regionUIVO.setParamStatus(townUIVO.getParamStatus());
		regionUIVO.setParamStatusId(townUIVO.getParamStatusId());
		return regionUIVO;
	}
}
