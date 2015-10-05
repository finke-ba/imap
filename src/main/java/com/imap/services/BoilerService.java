package com.imap.services;

import com.imap.dao.BoilerDao;
import com.imap.dao.BoilerRegionDao;
import com.imap.dao.BoilerTownDao;
import com.imap.domain.Boiler;
import com.imap.domain.BoilerRegion;
import com.imap.domain.BoilerTown;
import com.imap.dto.ResponseBoiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerService {

	@Autowired
	private BoilerRegionDao boilerRegionDao;

	@Autowired
	private BoilerTownDao boilerTownDao;

	@Autowired
	private BoilerDao boilerDao;

	public List<BoilerRegion> getBoilersForRegion() {
		return boilerRegionDao.getBoilers();
	}

	public List<BoilerTown> getBoilersForTown(Integer id) {
		return boilerTownDao.getBoilers(id);
	}

	public Boiler getBoiler(int id) {
		return boilerDao.getBoiler(id);
	}

}
