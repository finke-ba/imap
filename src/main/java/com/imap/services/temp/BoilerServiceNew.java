package com.imap.services.temp;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.jdbc.BoilerAPV;
import com.imap.domain.jdbc.ControlObject;
import com.imap.domain.jpa.ActualParamValue;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerServiceNew extends AbstractBoilerServiceNew<BoilerUIVO> {

	@Autowired
	private BoilersAPVDao boilersAPVDao;

	public List<BoilerUIVO> getBoilerChecked(int boilerId) {
		Map<Integer, Map<Integer, BoilerAPV>> townMap = boilersAPVDao.getTownMap();
		Map<Integer, Integer> boilerTownMap = boilersAPVDao.getBoilerTownMap();
		Integer townId = boilerTownMap.get(boilerId);
		return checkBoiler(townMap.get(townId).get(boilerId).getControlObjects());
	}

	@Override
	public BoilerUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		BoilerUIVO boilerUIVO = new BoilerUIVO();
		boilerUIVO.setParamStatus(uivo.getParamStatus());
		boilerUIVO.setParamStatusId(uivo.getParamStatusId());
		boilerUIVO.setParamName(controlObject.getParamName());
		boilerUIVO.setParamValue(controlObject.getParamValue().toString());
		boilerUIVO.setDate(controlObject.getDate());
		return boilerUIVO;
	}

}
