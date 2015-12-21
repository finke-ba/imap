package com.imap.services;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.BoilerAPV;
import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerTownUIVO;
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
public class BoilerService extends AbstractBoilerService<BoilerUIVO> {

	@Autowired
	private BoilersAPVDao boilersAPVDao;

	public BoilerTownUIVO getBoiler(int id) {
		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		Integer townId = boilersAPVDao.getBoilerTownMap().get(id);
		Map<Integer, BoilerAPV> townMap = boilersAPVDao.getTownMap().get(townId);
		BoilerAPV boilerAPV = townMap.get(id);
		boilerTownUIVO.setBoilerName(boilerAPV.getBoilerName());
		boilerTownUIVO.setAddress(boilerAPV.getBoilerAddress());
		boilerTownUIVO.setTownName(boilerAPV.getTownName());
		boilerTownUIVO.setTownId(townId);
		return boilerTownUIVO;
	}

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
		boilerUIVO.setDate(controlObject.getDate().toString());
		return boilerUIVO;
	}

}
