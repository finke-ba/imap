package com.imap.services;

import com.imap.dao.BoilersDao;
import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации об определенных котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerService extends AbstractBoilerService<BoilerUIVO> {

	/**
	 * Получает информацию об определенной котельнной по её идентификатору.
	 *
	 * @param id - идентификатор котельной
	 * @return информация о котельной
	 */
	public BoilerTownUIVO getBoiler(int id) {
		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		Integer townId = boilersDao.getBoilerTownMap().get(id);
		Map<Integer, Boiler> townMap = boilersDao.getTownMap().get(townId);
		Boiler boiler = townMap.get(id);
		boilerTownUIVO.setBoilerName(boiler.getBoilerName());
		boilerTownUIVO.setAddress(boiler.getBoilerAddress());
		boilerTownUIVO.setTownName(boiler.getTownName());
		boilerTownUIVO.setTownId(townId);
		return boilerTownUIVO;
	}

	/**
	 * Получает список проверенных приборов учета на одной котельной.
	 *
	 * @param boilerId - идентификатор котельной
	 * @return список проверенных приборов учета на котельной
	 */
	public List<BoilerUIVO> getBoilerChecked(int boilerId) {
		Map<Integer, Map<Integer, Boiler>> townMap = boilersDao.getTownMap();
		Map<Integer, Integer> boilerTownMap = boilersDao.getBoilerTownMap();
		Integer townId = boilerTownMap.get(boilerId);
		return checkBoiler(townMap.get(townId).get(boilerId).getControlObjects());
	}

	@Override
	protected BoilerUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		BoilerUIVO boilerUIVO = new BoilerUIVO();
		boilerUIVO.setParamStatus(uivo.getParamStatus());
		boilerUIVO.setParamStatusId(uivo.getParamStatusId());
		boilerUIVO.setParamName(controlObject.getParamName());
		boilerUIVO.setParamValue(controlObject.getParamValue().toString());
		boilerUIVO.setDate(controlObject.getDate().toString());
		return boilerUIVO;
	}

}
