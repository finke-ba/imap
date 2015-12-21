package com.imap.services;

import com.imap.dao.BoilersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class UpdateMonitor {

	@Autowired
	BoilersDao boilersDao;

	@Scheduled(fixedDelay = 300_000)
	public void update() {
		boilersDao.updateBoilersMap();
	}
}