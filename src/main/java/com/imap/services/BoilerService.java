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

	private final double xt = 8;

	public List<BoilerRegion> getBoilersForRegion() {
		return boilerRegionDao.getBoilers();
	}

	public List<BoilerTown> getBoilersForTown(Integer id) {
		return boilerTownDao.getBoilers(id);
	}

	public List<Boiler> getBoiler(int id) {
		return CheckBoiler(boilerDao.getBoiler(id));
	}

	private List<Boiler> CheckBoiler(List<Boiler> boilers) {
		Boiler boiler1 = boilers.get(0);
		Boiler boiler2 = boilers.get(1);
		Boiler boiler3 = boilers.get(2);

		Double y1 = 2.2294 * (8 - xt) + 52.386;
		Double y2 = 0.7748 * (8 - xt) + 36.386;
		Double y3 = 1.2294 * (8 - xt) + 41.386;

		if ( !(boiler1.getParamValue() >= (y1 - 10) && boiler1.getParamValue() <= (y1 + 10)) ){
//			boiler1.setParamValue(y1.intValue());
			boiler1.setReason(String.format("Показания вышли за пределы +/- 10°C %s", y1.toString()));
		} else {
			boiler1.setReason(String.format("Предел %s", y1.toString()));
		}
		if ( !(boiler2.getParamValue() >= (y2 - 10) && boiler2.getParamValue() <= (y2 + 10)) ){
//			boiler2.setParamValue(y2.intValue());
			boiler2.setReason(String.format("Показания вышли за пределы +/- 10°C %s", y2.toString()));
		} else {
			boiler2.setReason(String.format("Предел %s", y2.toString()));
		}
		if ( !(boiler3.getParamValue() >= (y3 - 10) && boiler1.getParamValue() <= (y3 + 10)) ){
//			boiler3.setParamValue(y3.intValue());
			boiler3.setReason(String.format("Показания вышли за пределы +/- 10°C %s", y3.toString()));
		} else {
			boiler3.setReason(String.format("Предел %s", y3.toString()));
		}

		return boilers;
	}

}
