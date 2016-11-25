package com.imap.services.impl;

import com.imap.dao.BoilerInfoDao;
import com.imap.exceptions.NoSuchItemException;
import com.imap.services.ValidationCacheService;
import com.imap.services.BoilerService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для получения информации о котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerServiceImpl implements BoilerService {

	/** Интерфейс доступа к кэшированным данным по котельным. */
	private final ValidationCacheService validationCacheService;

	/** Интерфейс доступа к информации о котельной. */
	private final BoilerInfoDao boilerInfoDao;

	@Autowired
	public BoilerServiceImpl (final ValidationCacheService validationCacheService, final BoilerInfoDao boilerInfoDao) {
		this.validationCacheService = validationCacheService;
		this.boilerInfoDao = boilerInfoDao;
	}

	@Override
	public TownUIVO getBoiler(int id) throws NoSuchItemException {
		TownUIVO boilerInfo = boilerInfoDao.getBoilerInfo(id);
		if (boilerInfo == null) {
			throw new NoSuchItemException();
		}
		return boilerInfo;
	}

	@Override
	public List<BoilerUIVO> getBoilerChecked(int boilerId) throws NoSuchItemException {
		return validationCacheService.getBoilerChecked(boilerId);
	}

}
