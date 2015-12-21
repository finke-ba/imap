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

import static com.imap.services.BoilerTownService.*;

/**
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
	 * @param id идентификатор города
	 * @return данные о проверенном городе
	 */
	private BoilerRegionUIVO addCheckedTown(List<BoilerTownUIVO> townUIVOs, Integer id) {
		BoilerRegionUIVO regionUIVO = new BoilerRegionUIVO();

		regionUIVO.setTownId(id);
		if(!townUIVOs.isEmpty()) {
			//Приборы учета для одной котельной
			boolean isGreen = false;
			for (BoilerTownUIVO townUIVO : townUIVOs) {
				regionUIVO.setParamStatusId(townUIVO.getParamStatusId());
				regionUIVO.setParamStatus("Снятие показаний не производится");
				if(townUIVO.getParamStatusId().equals(PARAM_STATUS_RED)) {
					regionUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return regionUIVO;
				}
				if(townUIVO.getParamStatusId().equals(PARAM_STATUS_GREEN)) {
					isGreen = true;
				}
			}
			if (isGreen) {
				regionUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				regionUIVO.setParamStatus("Показания в рамках допустимых пределов");
			}
		} else {
			regionUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
			regionUIVO.setParamStatus("Снятие показаний не производится");
		}

		return regionUIVO;
	}
}
