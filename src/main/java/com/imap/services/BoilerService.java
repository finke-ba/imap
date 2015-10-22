package com.imap.services;

import com.imap.dao.BoilerDao;
import com.imap.dao.BoilerRegionDao;
import com.imap.domain.jpa.ControlObjects;
import com.imap.repository.BoilerRepository;
import com.imap.dao.BoilerTownDao;
import com.imap.domain.jdbc.BoilerRegion;
import com.imap.domain.jdbc.BoilerTown;
import com.imap.repository.ControlObjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

	@Autowired
	private BoilerRepository boilerRepository;

	@Autowired
	private ControlObjectsRepository controlObjectsRepository;

	private final static double XT = 8;

	private final static Integer PARAM_STATUS_GREEN = 1;

	private final static Integer PARAM_STATUS_YELLOW = 2;

	private final static Integer PARAM_STATUS_RED = 3;

	private final static Integer NULL_BOILER_ID = 0;

	public List<BoilerRegion> getBoilersForRegion() {
		return boilerRegionDao.getBoilers();
	}

	public List<BoilerTown> getBoilersForTown(Integer id) {
		return CheckBoilerForTown(boilerTownDao.getBoilers(id));
	}

	public List<com.imap.domain.jdbc.Boiler> getBoiler(int id) {
		return CheckBoiler(boilerDao.getBoiler(id));
	}

	private List<BoilerTown> CheckBoilerForTown(List<BoilerTown> boilers) {

		Double y1 = 2.2294 * (8 - XT) + 52.386;
		Double y2 = 0.7748 * (8 - XT) + 36.386;
		Double y3 = 1.2294 * (8 - XT) + 41.386;

		List<BoilerTown> result = new ArrayList<>();

		int index = 0;
		for (BoilerTown boiler : boilers) {
			if( boiler.getParamValue().equals(NULL_BOILER_ID)) {
				BoilerTown boilerTown = new BoilerTown();
				boilerTown.setBoilerId(boiler.getBoilerId());
				boilerTown.setName(boiler.getName());
				boilerTown.setAddress(boiler.getAddress());
				boilerTown.setParamStatus(String.format("Снятие показаний не производится"));
				boilerTown.setParamStatusId(PARAM_STATUS_YELLOW);
				result.add(boilerTown);
				index++;
			}
		}

		List<BoilerTown> boilersWithoutNotCheckedBoiler = boilers.subList(index, boilers.size());


		for (int i = 0; i<boilersWithoutNotCheckedBoiler.size(); i = i +3) {
			BoilerTown boilerTown = new BoilerTown();
			BoilerTown boilerTown1 = boilersWithoutNotCheckedBoiler.get(i);
			BoilerTown boilerTown2 = boilersWithoutNotCheckedBoiler.get(i + 1);
			BoilerTown boilerTown3 = boilersWithoutNotCheckedBoiler.get(i + 2);

			boilerTown.setBoilerId(boilerTown1.getBoilerId());
			boilerTown.setName(boilerTown1.getName());
			boilerTown.setAddress(boilerTown1.getAddress());

			if ((boilerTown1.getParamValue() >= (y1 - 10) && boilerTown1.getParamValue() <= (y1 + 10))
					|| (boilerTown2.getParamValue() >= (y2 - 10) && boilerTown2.getParamValue() <= (y2 + 10))
					|| (boilerTown3.getParamValue() >= (y3 - 10) && boilerTown3.getParamValue() <= (y3 + 10))) {
				boilerTown.setParamStatus(String.format("Показания в рамках допустимых пределов"));
				boilerTown.setParamStatusId(PARAM_STATUS_GREEN);
			} else {
				boilerTown.setParamStatus(String.format("Показания вышли за допустимые пределы"));
				boilerTown.setParamStatusId(PARAM_STATUS_RED);
			}

			result.add(boilerTown);
		}

		Collections.sort(result, (o1, o2) -> o1.getBoilerId().compareTo(o2.getBoilerId()));
		return result;
	}

	private List<com.imap.domain.jdbc.Boiler> CheckBoiler(List<com.imap.domain.jdbc.Boiler> boilers) {
		com.imap.domain.jdbc.Boiler boiler1 = boilers.get(0);
		com.imap.domain.jdbc.Boiler boiler2 = boilers.get(1);
		com.imap.domain.jdbc.Boiler boiler3 = boilers.get(2);

		Double y1 = 2.2294 * (8 - XT) + 52.386;
		Double y2 = 0.7748 * (8 - XT) + 36.386;
		Double y3 = 1.2294 * (8 - XT) + 41.386;

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

	public List<ControlObjects> getBoilerNew(int id) {
		return controlObjectsRepository.findByBoilerId(id);
	}

	public List<ControlObjects> getBoilersForTownNew(int id) {
		return controlObjectsRepository.findByTownId(id);
	}

	public List<ControlObjects> getBoilersForRegionNew() {
		return controlObjectsRepository.findAll();
	}
}
