package com.imap.services;

import com.imap.dao.BoilersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Сервис для обновления данных из БД в памяти приложения.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class UpdateMonitor {

	/** Интерфейс доступа к данным по котельным. */
	@Autowired
	BoilersDao boilersDao;

	/** Обновляет раз в 5 минут данные о котельных в памяти. */
	@Scheduled(fixedDelay = 300_000)
	public void update() {
		boilersDao.updateBoilersMap();
	}
}