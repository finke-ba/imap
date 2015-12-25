package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.UIVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerTownService extends AbstractBoilerService<BoilerTownUIVO> {

	/**
	 * Получает информацию об определенном городе по его идентификатору.
	 *
	 * @param id идентификатор города
	 * @return информацию городе
	 */
	public BoilerTownUIVO getTown(Integer id) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		Map<Integer, Boiler> boilerMap = boilersDao.getTownMap().get(id);
		if (!boilerMap.isEmpty()) {
			Map.Entry<Integer, Boiler> boilerAPVEntry = boilerMap.entrySet().iterator().next();
			townUIVO.setTownName(boilerAPVEntry.getValue().getTownName());
		}
		return townUIVO;
	}

	/**
	 * Получает список проверенных котельных в определенном городе.
	 *
	 * @param id идентификатор города
	 * @return список проверенных котельных
	 */
	public List<BoilerTownUIVO> getBoilersInTownChecked(int id) {
		Map<Integer, Map<Integer, Boiler>> townMap = boilersDao.getTownMap();
		return checkTown(townMap.get(id));
	}

	/**
	 * Проверяет все котельные в одном городе.
	 *
	 * @param controlObjects список котельных в городе
	 * @return список проверенных котельных в городе
	 */
	public List<BoilerTownUIVO> checkTown(Map<Integer, Boiler> controlObjects) {
		List<BoilerTownUIVO> boilerTownUIVOs = new ArrayList<>();

		controlObjects.forEach((k, v) -> boilerTownUIVOs.add(addCheckedBoiler(checkBoiler(v.getControlObjects()), v)));

		return boilerTownUIVOs;
	}

	/**
	 * Преобразует список проверенных приборов учета котельной в объект, содержащий данные проверки этой котельной.
	 *
	 * @param boilerTownUIVOs список проверенных приборов учета котельной
	 * @param boiler информация о котельной
	 * @return данные о проверенной котельной
	 */
	private BoilerTownUIVO addCheckedBoiler(List<BoilerTownUIVO> boilerTownUIVOs, Boiler boiler) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		townUIVO.setBoilerId(boiler.getBoilerId());
		townUIVO.setBoilerName(boiler.getBoilerName());
		townUIVO.setAddress(boiler.getBoilerAddress());
		return checkAddedUIVO(townUIVO, boilerTownUIVOs);
	}

	public BoilerTownUIVO checkAddedUIVO (BoilerTownUIVO townUIVO, List<BoilerTownUIVO> boilerTownUIVOs) {
		if(!boilerTownUIVOs.isEmpty()) {
			boolean isGreen = false;
			for (UIVO boilerTownUIVO : boilerTownUIVOs) {
				townUIVO.setParamStatusId(boilerTownUIVO.getParamStatusId());
				townUIVO.setParamStatus("Снятие показаний не производится");
				if(boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_RED)) {
					townUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return townUIVO;
				}
				if(boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_GREEN)) {
					isGreen = true;
				}
			}
			if (isGreen) {
				townUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				townUIVO.setParamStatus("Показания в рамках допустимых пределов");
			}
		} else {
			townUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
			townUIVO.setParamStatus("Снятие показаний не производится");
		}
		return townUIVO;
	}

	@Override
	protected BoilerTownUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		boilerTownUIVO.setParamStatus(uivo.getParamStatus());
		boilerTownUIVO.setParamStatusId(uivo.getParamStatusId());

		return boilerTownUIVO;
	}

}
