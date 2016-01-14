package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.exceptions.NoSuchItemException;
import com.imap.services.BoilerMapService;
import com.imap.services.TownService;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class TownServiceImpl implements TownService {

	/** Интерфейс доступа к данным по котельным. */
	@Autowired
	protected BoilerMapService boilerMapService;

	@Override
	public TownUIVO getTown(Integer id) throws NoSuchItemException {
		TownUIVO townUIVO = new TownUIVO();
		Map<Integer, Boiler> boilerMap = boilerMapService.getTownMap().get(id);

		if (boilerMap == null || boilerMap.isEmpty()) {
			throw new NoSuchItemException();
		}

		Map.Entry<Integer, Boiler> boilerAPVEntry = boilerMap.entrySet().iterator().next();
		townUIVO.setTownName(boilerAPVEntry.getValue().getTownName());

		return townUIVO;
	}

	@Override
	public List<TownUIVO> getTownChecked(int id) throws NoSuchItemException {
		Map<Integer, List<TownUIVO>> townChecked = boilerMapService.getTownChecked();
		if (townChecked == null || townChecked.isEmpty()) {
			throw new NoSuchItemException();
		}
		return townChecked.get(id);
	}


}
