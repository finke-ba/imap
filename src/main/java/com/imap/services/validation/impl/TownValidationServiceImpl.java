package com.imap.services.validation.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.validation.TownValidationService;
import com.imap.uivo.TownUIVO;
import com.imap.uivo.UIVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class TownValidationServiceImpl extends AbstractBoilerValidationService<TownUIVO> implements TownValidationService {

	@Override
	public Map<Integer, List<TownUIVO>> getTownMapChecked(Map<Integer, Map<Integer, Boiler>> townMap) {
		Map<Integer, List<TownUIVO>> townMapChecked = new HashMap<>();

		townMap.forEach((townId, boilerMap) -> townMapChecked.put(
				townId,
				getTownChecked(
						townMap.get(townId)
				)
		));

		return townMapChecked;
	}

	@Override
	public List<TownUIVO> getTownChecked(Map<Integer, Boiler> boilerMap) {
		List<TownUIVO> townUIVOs = new ArrayList<>();

		boilerMap.forEach((k, v) -> townUIVOs.add(addCheckedBoiler(checkBoiler(v.getControlObjects()), v)));

		return townUIVOs;
	}

	/**
	 * Преобразует список проверенных приборов учета котельной в объект, содержащий данные проверки этой котельной.
	 *
	 * @param townUIVOs список проверенных приборов учета котельной
	 * @param boiler    информация о котельной
	 * @return данные о проверенной котельной
	 */
	private TownUIVO addCheckedBoiler(List<TownUIVO> townUIVOs, Boiler boiler) {
		TownUIVO townUIVO = new TownUIVO();
		townUIVO.setBoilerId(boiler.getBoilerId());
		townUIVO.setBoilerName(boiler.getBoilerName());
		townUIVO.setBoilerAddress(boiler.getBoilerAddress());
		return mapCheckedTown(townUIVO, townUIVOs);
	}

	@Override
	public TownUIVO mapCheckedTown(TownUIVO townUIVO, List<TownUIVO> townUIVOs) {
		if (!townUIVOs.isEmpty()) {
			boolean isGreen = false;
			for (UIVO boilerTownUIVO : townUIVOs) {
				townUIVO.setParamStatusId(boilerTownUIVO.getParamStatusId());
				townUIVO.setParamStatus("Снятие показаний не производится");
				if (boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_RED)) {
					townUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return townUIVO;
				}
				if (boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_GREEN)) {
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
	protected TownUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		TownUIVO townUIVO = new TownUIVO();
		townUIVO.setParamStatus(uivo.getParamStatus());
		townUIVO.setParamStatusId(uivo.getParamStatusId());
		return townUIVO;
	}

}
