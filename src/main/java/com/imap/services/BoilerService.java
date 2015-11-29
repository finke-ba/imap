package com.imap.services;

import com.imap.dao.BoilerDao;
import com.imap.dao.BoilerRegionDao;
import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.Boiler;
import com.imap.domain.jpa.ControlObject;
import com.imap.repository.BoilerRepository;
import com.imap.dao.BoilerTownDao;
import com.imap.domain.jdbc.BoilerRegion;
import com.imap.domain.jdbc.BoilerTown;
import com.imap.repository.ControlObjectRepository;
import com.imap.uivo.UIVO;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerService extends AbstractBoilerService<BoilerUIVO> {

	@Autowired
	private BoilerRegionDao boilerRegionDao;

	@Autowired
	private BoilerTownDao boilerTownDao;

	@Autowired
	private BoilerDao boilerDao;

	private final static Integer NULL_BOILER_ID = 0;

	public List<BoilerRegion> getBoilersForRegion() {
		return boilerRegionDao.getBoilers();
	}

	public List<BoilerTown> getBoilersForTown(Integer id) {
		return CheckBoilerForTown(boilerTownDao.getBoilers(id));
	}

	public List<com.imap.domain.jdbc.Boiler> getBoilerJdbc(int id) {
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
				boilerTown.setParamStatus("Снятие показаний не производится");
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
				boilerTown.setParamStatus("Показания в рамках допустимых пределов");
				boilerTown.setParamStatusId(PARAM_STATUS_GREEN);
			} else {
				boilerTown.setParamStatus("Показания вышли за допустимые пределы");
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

	public List<List<BoilerUIVO>> getBoilerCheck(int id) {
		return CheckBoilerNew(controlObjectRepository.findByBoilerId(id));
	}

	@Override
	public List<BoilerUIVO> checkBoilerControlObject(ControlObject controlObject) {
		List<BoilerUIVO> boilersUIVO = new ArrayList<>();
		List<ActualParamValue> actualParamValues = controlObject.getActualParamValues();

		for (ActualParamValue actualParamValue : actualParamValues) {
			if (ID_PD_T1.equals(actualParamValue.getIdParamDescription())) {
				boilersUIVO.add(checkParamValue(actualParamValue, Y1));
			} else if (ID_PD_T2.equals(actualParamValue.getIdParamDescription())) {
				boilersUIVO.add(checkParamValue(actualParamValue, Y2));
			} else if (ID_PD_T3.equals(actualParamValue.getIdParamDescription())) {
				boilersUIVO.add(checkParamValue(actualParamValue, Y3));
			}
		}

		return boilersUIVO;
	}

	@Override
	public BoilerUIVO checkParamValue(ActualParamValue actualParamValue, Double y) {
		BoilerUIVO boilerUIVO = new BoilerUIVO();

		UIVO uivo = super.checkParamValue(actualParamValue, y);

		boilerUIVO.setParamName(actualParamValue.getParName());
		boilerUIVO.setDate(actualParamValue.getDateTime());
		boilerUIVO.setParamValue(actualParamValue.getParValue());
		boilerUIVO.setParamStatus(uivo.getParamStatus());
		boilerUIVO.setParamStatusId(uivo.getParamStatusId());

		return boilerUIVO;
	}


	public Boiler getBoiler(int id) {
		return boilerRepository.findOne(id);
	}
}
