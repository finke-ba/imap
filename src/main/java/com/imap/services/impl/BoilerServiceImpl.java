package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.AbstractBoilerService;
import com.imap.services.BoilerService;
import com.imap.uivo.TownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации об определенных котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerServiceImpl extends AbstractBoilerService<BoilerUIVO> implements BoilerService {

	@Override
	public TownUIVO getBoiler(int id) {
		TownUIVO townUIVO = new TownUIVO();
		Integer townId = boilerMapService.getBoilerTownMap().get(id);
		Map<Integer, Boiler> townMap = boilerMapService.getTownMap().get(townId);
		Boiler boiler = townMap.get(id);
		townUIVO.setBoilerName(boiler.getBoilerName());
		townUIVO.setAddress(boiler.getBoilerAddress());
		townUIVO.setTownName(boiler.getTownName());
		townUIVO.setTownId(townId);
		return townUIVO;
	}

	@Override
	public List<BoilerUIVO> getBoilerChecked(int boilerId) {
		Map<Integer, Map<Integer, Boiler>> townMap = boilerMapService.getTownMap();
		Map<Integer, Integer> boilerTownMap = boilerMapService.getBoilerTownMap();
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
