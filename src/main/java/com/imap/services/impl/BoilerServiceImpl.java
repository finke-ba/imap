package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.exceptions.NoSuchItemException;
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
	public TownUIVO getBoiler(int id) throws NoSuchItemException {
		Map<Integer, Integer> boilerTownMap = boilerMapService.getBoilerTownMap();
		if (boilerTownMap == null || boilerTownMap.isEmpty()) {
			throw new NoSuchItemException();
		}

		Map<Integer, Map<Integer, Boiler>> townMap = boilerMapService.getTownMap();
		if (townMap == null || townMap.isEmpty()) {
			throw new NoSuchItemException();
		}

		TownUIVO townUIVO = new TownUIVO();
		Integer townId = boilerTownMap.get(id);
		Map<Integer, Boiler> boilerMap = townMap.get(townId);
		Boiler boiler = boilerMap.get(id);
		townUIVO.setBoilerName(boiler.getBoilerName());
		townUIVO.setAddress(boiler.getBoilerAddress());
		townUIVO.setTownName(boiler.getTownName());
		townUIVO.setTownId(townId);
		return townUIVO;
	}

	@Override
	public List<BoilerUIVO> getBoilerChecked(int boilerId) throws NoSuchItemException {
		Map<Integer, Integer> boilerTownMap = boilerMapService.getBoilerTownMap();
		if (boilerTownMap == null || boilerTownMap.isEmpty()) {
			throw new NoSuchItemException();
		}

		Map<Integer, Map<Integer, Boiler>> townMap = boilerMapService.getTownMap();
		if (townMap == null || townMap.isEmpty()) {
			throw new NoSuchItemException();
		}

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
