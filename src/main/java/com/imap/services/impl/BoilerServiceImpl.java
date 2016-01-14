package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.exceptions.NoSuchItemException;
import com.imap.services.BoilerMapService;
import com.imap.services.BoilerService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Сервис для получения информации о котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerServiceImpl implements BoilerService {

	/** Интерфейс доступа к данным по котельным. */
	@Autowired
	protected BoilerMapService boilerMapService;

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
		townUIVO.setBoilerAddress(boiler.getBoilerAddress());
		townUIVO.setTownName(boiler.getTownName());
		townUIVO.setTownId(townId);
		return townUIVO;
	}

	@Override
	public List<BoilerUIVO> getBoilerChecked(int boilerId) throws NoSuchItemException {
		Map<Integer, List<BoilerUIVO>> boilerChecked = boilerMapService.getBoilerChecked();
		if (boilerChecked == null || boilerChecked.isEmpty()) {
			throw new NoSuchItemException();
		}
		return boilerChecked.get(boilerId);
	}

}
