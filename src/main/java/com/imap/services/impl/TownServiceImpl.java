package com.imap.services.impl;

import com.imap.dao.TownInfoDao;
import com.imap.exceptions.NoSuchItemException;
import com.imap.services.ValidationCacheService;
import com.imap.services.TownService;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class TownServiceImpl implements TownService {

	/** Интерфейс доступа к кэшированным данным по городам. */
	@Autowired
	protected ValidationCacheService validationCacheService;

	/** Интерфейс доступа к информации о городе. */
	@Autowired
	protected TownInfoDao townInfoDao;

	@Override
	public TownUIVO getTown(Integer id) throws NoSuchItemException {
		TownUIVO townInfo = townInfoDao.getTownInfo(id);
		if (townInfo == null) {
			throw new NoSuchItemException();
		}
		return townInfo;
	}

	@Override
	public List<TownUIVO> getTownChecked(int id) throws NoSuchItemException {
		return validationCacheService.getTownChecked(id);
	}


}
