package com.imap.services;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.ControlObject;
import com.imap.domain.jpa.Town;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerRegionService extends AbstractBoilerService<BoilerRegionUIVO> {

	@Autowired
	TownRepository townRepository;

	public List<BoilerRegionUIVO> getBoilersForRegionCheckNew() {
		return CheckBoiler(controlObjectRepository.findAll());
	}

	public BoilerTownUIVO getTownById(Integer id) {
		Town town = townRepository.findOne(id);
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		townUIVO.setName(town.getRu_city());
		return townUIVO;
	}


	public List<BoilerRegionUIVO> CheckBoiler(List<ControlObject> controlObjectsForBoiler) {
		List<Town> towns = townRepository.findAll();
		List<BoilerRegionUIVO> boilerUIVOsList = new ArrayList<>();
		for (ControlObject controlObject : controlObjectsForBoiler) {
			boilerUIVOsList.add(checkBoilerControlObjectNew(controlObject));
		}
		HashMap<Integer, BoilerRegionUIVO> hmTownsIds = new HashMap<>();
		HashSet<Integer> hsTownsIds = new HashSet<>();
		for (int i=0; i< towns.size(); i++) {
			BoilerRegionUIVO boilerRegionUIVO = new BoilerRegionUIVO();
			boilerRegionUIVO.setParamStatusId(PARAM_STATUS_GREEN);
			boilerRegionUIVO.setParamStatus("Показания в рамках допустимых пределов");
			boilerRegionUIVO.setTownId(towns.get(i).getId());
			hmTownsIds.put(towns.get(i).getId(), boilerRegionUIVO);
			hsTownsIds.add(towns.get(i).getId());
		}
		for (BoilerRegionUIVO boilerRegionUIVO : boilerUIVOsList) {
			if (PARAM_STATUS_RED.equals(boilerRegionUIVO.getParamStatusId())) {
				hmTownsIds.put(boilerRegionUIVO.getTownId(), boilerRegionUIVO);
			}
			hsTownsIds.remove(boilerRegionUIVO.getTownId());
		}
		for (int i=0; i< towns.size(); i++) {
			if (hsTownsIds.contains(towns.get(i).getId())) {
				BoilerRegionUIVO boilerRegionUIVO = new BoilerRegionUIVO();
				boilerRegionUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
				boilerRegionUIVO.setParamStatus("Снятие показаний не производится");
				boilerRegionUIVO.setTownId(towns.get(i).getId());
				hmTownsIds.put(towns.get(i).getId(), boilerRegionUIVO);
			}
		}
		List<BoilerRegionUIVO> resultList = new ArrayList<>();
		for (int i=0; i< towns.size(); i++) {
			BoilerRegionUIVO hmBoilerUIVO = hmTownsIds.get(towns.get(i).getId());
			BoilerRegionUIVO result = new BoilerRegionUIVO();
			result.setTownId(hmBoilerUIVO.getTownId());
			result.setParamStatusId(hmBoilerUIVO.getParamStatusId());
			result.setParamStatus(hmBoilerUIVO.getParamStatus());
			resultList.add(result);
		}

//		return boilerUIVOsList;
		return resultList;
	}


	public BoilerRegionUIVO checkBoilerControlObjectNew(ControlObject controlObject) {
//		BoilerRegionUIVO boilersUIVO = new ArrayList<>();

		BoilerRegionUIVO boilerRegionUIVO = new BoilerRegionUIVO();
		BoilerRegionUIVO resultList = new BoilerRegionUIVO();

		boilerRegionUIVO.setTownId(controlObject.getBoiler().getTown().getId());

		List<ActualParamValue> actualParamValues = controlObject.getActualParamValues();
		if (actualParamValues.size() > 0) {
			Integer paramStatusId1 = 0;
			Integer paramStatusId2 = 0;
			Integer paramStatusId3 = 0;
			for (ActualParamValue actualParamValue : actualParamValues) {
				if (ID_PD_T1.equals(actualParamValue.getIdParamDescription())) {
					paramStatusId1 = checkParamValue(actualParamValue, Y1).getParamStatusId();
				} else if (ID_PD_T2.equals(actualParamValue.getIdParamDescription())) {
					paramStatusId2 = checkParamValue(actualParamValue, Y2).getParamStatusId();
				} else if (ID_PD_T3.equals(actualParamValue.getIdParamDescription())) {
					paramStatusId3 = checkParamValue(actualParamValue, Y3).getParamStatusId();
				}
			}
			if (PARAM_STATUS_GREEN.equals(paramStatusId1) &&
					PARAM_STATUS_GREEN.equals(paramStatusId2) &&
					PARAM_STATUS_GREEN.equals(paramStatusId3)) {
				boilerRegionUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				boilerRegionUIVO.setParamStatus("Показания в рамках допустимых пределов");
			} else {
				boilerRegionUIVO.setParamStatusId(PARAM_STATUS_RED);
				boilerRegionUIVO.setParamStatus("Показания вышли за допустимые пределы");
			}
		} else {
			boilerRegionUIVO.setParamStatus("Снятие показаний не производится");
			boilerRegionUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
		}
//		boilersUIVO.add(boilerRegionUIVO);
//		return boilersUIVO;
		return boilerRegionUIVO;
	}

	public List<BoilerRegionUIVO> checkBoilerControlObject(ControlObject controlObject){
		List<BoilerRegionUIVO> boilersUIVO = new ArrayList<>();
		BoilerRegionUIVO boilerRegionUIVO = new BoilerRegionUIVO();
		boilersUIVO.add(boilerRegionUIVO);

		return boilersUIVO;
	}


}
