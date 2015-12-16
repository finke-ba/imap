package com.imap.services.temp;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.jdbc.BoilerAPV;
import com.imap.domain.jdbc.BoilerTown;
import com.imap.domain.jdbc.ControlObject;
import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.Town;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerTownServiceNew extends AbstractBoilerServiceNew<BoilerTownUIVO> {

	@Autowired
	protected TownRepository townRepository;

	@Autowired
	private BoilersAPVDao boilersAPVDao;

	public List<BoilerTownUIVO> getBoilersInTownChecked(int id) {
		Map<Integer, Map<Integer, BoilerAPV>> townMap = boilersAPVDao.getTownMap();
		return checkTown(townMap.get(id));
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
		townUIVO.setName(boiler.getBoilerName());
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
