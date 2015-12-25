package com.imap.services;

import com.imap.dao.BoilersDao;
import com.imap.domain.Boiler;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
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
public class BoilerRegionService {

	/** Сервис для проверки данных котельных в городе. */
	@Autowired
	private BoilerTownService boilerTownService;

	/** Интерфейс доступа к данным по котельным в БД. */
	@Autowired
	private BoilersDao boilersDao;

	/**
	 * Получает список проверенных городов в текущем регионе.
	 *
	 * @return список проверенных городов
	 */
	public List<BoilerRegionUIVO> getBoilersForRegionCheck() {
		final Map<Integer, Map<Integer, Boiler>> townMap = boilersDao.getTownMap();

		List<BoilerRegionUIVO> regionUIVOs = new ArrayList<>();
		townMap.forEach((k, v) -> regionUIVOs.add(addCheckedTown(boilerTownService.checkTown(v), k)));

		return regionUIVOs;
	}

	/**
	 * Преобразует список проверенных котельных в городе в объект, содержащий данные проверки города.
	 *
	 * @param townUIVOs список проверенных котельных в городе
	 * @param townId идентификатор города
	 * @return данные о проверенном городе
	 */
	private BoilerRegionUIVO addCheckedTown(List<BoilerTownUIVO> townUIVOs, Integer townId) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		townUIVO.setTownId(townId);
		return mapBoilerRegionUIVO(boilerTownService.checkAddedUIVO(townUIVO, townUIVOs));
	}

	private BoilerRegionUIVO mapBoilerRegionUIVO(BoilerTownUIVO boilerTownUIVO) {
		BoilerRegionUIVO regionUIVO = new BoilerRegionUIVO();
		regionUIVO.setTownId(boilerTownUIVO.getTownId());
		regionUIVO.setParamStatus(boilerTownUIVO.getParamStatus());
		regionUIVO.setParamStatusId(boilerTownUIVO.getParamStatusId());
		return regionUIVO;
	}
}
