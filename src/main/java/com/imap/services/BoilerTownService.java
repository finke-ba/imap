package com.imap.services;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.BoilerAPV;
import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerTownService extends AbstractBoilerService<BoilerTownUIVO> {

	@Autowired
	private BoilersAPVDao boilersAPVDao;

	public List<BoilerTownUIVO> getBoilersInTownChecked(int id) {
		Map<Integer, Map<Integer, BoilerAPV>> townMap = boilersAPVDao.getTownMap();
		return checkTown(townMap.get(id));
	}

	public BoilerTownUIVO getTown(Integer id) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		Map<Integer, BoilerAPV> boilerMap = boilersAPVDao.getTownMap().get(id);
		if (!boilerMap.isEmpty()) {
			Map.Entry<Integer, BoilerAPV> boilerAPVEntry = boilerMap.entrySet().iterator().next();
			townUIVO.setTownName(boilerAPVEntry.getValue().getTownName());
		}
		return townUIVO;
	}

	/**
	 * Проверяет все котельные в одном городе.
	 *
	 * @param controlObjects список котельных в городе
	 * @return список проверенных котельных в городе
	 */
	public List<BoilerTownUIVO> checkTown(Map<Integer, BoilerAPV> controlObjects) {
		List<BoilerTownUIVO> boilerTownUIVOs = new ArrayList<>();

		controlObjects.forEach((k, v) -> boilerTownUIVOs.add(addCheckedBoiler(checkBoiler(v.getControlObjects()), v, k)));

		return boilerTownUIVOs;
	}

	public BoilerTownUIVO addCheckedBoiler(List<BoilerTownUIVO> boilerTownUIVOs, BoilerAPV boiler, Integer boilerId) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		townUIVO.setBoilerId(boilerId);
		townUIVO.setBoilerName(boiler.getBoilerName());
		townUIVO.setAddress(boiler.getBoilerAddress());

		if(!boilerTownUIVOs.isEmpty()) {
			//Приборы учета для одной котельной
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
	public BoilerTownUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		boilerTownUIVO.setParamStatus(uivo.getParamStatus());
		boilerTownUIVO.setParamStatusId(uivo.getParamStatusId());

		return boilerTownUIVO;
	}

}
