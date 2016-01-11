package com.imap.services.impl;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import com.imap.services.BoilerMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Класс для получения информации о всех приборах учета на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerMapServiceImpl implements BoilerMapService {

	@Autowired
	private BoilerDao boilerDao;

	/**
	 * Обновляет раз в 5 минут данные о котельных в памяти.
	 */
	@Scheduled(fixedDelay = 300_000)
	private void update() {
		boilerDao.updateBoilersMap();
	}

	@Override
	public Map<Integer, Map<Integer, Boiler>> getTownMap() {
		return boilerDao.getTownMap();
	}

	@Override
	public Map<Integer, Integer> getBoilerTownMap() {
		return boilerDao.getBoilerTownMap();
	}

}
