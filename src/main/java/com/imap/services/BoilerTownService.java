package com.imap.services;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.ControlObject;
import com.imap.domain.jpa.Town;
import com.imap.uivo.BoilerTownUIVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerTownService extends AbstractBoilerService<BoilerTownUIVO> {

	public List<List<BoilerTownUIVO>> getBoilersForTownCheck(int id) {
		return checkBoilerNew(controlObjectRepository.findByTownId(id));
	}

	@Override
	public List<BoilerTownUIVO> checkBoilerControlObject(ControlObject controlObject) {
		List<BoilerTownUIVO> boilersUIVO = new ArrayList<>();

		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		boilerTownUIVO.setBoilerId(controlObject.getBoiler().getId());
		boilerTownUIVO.setName(controlObject.getBoiler().getName());
		boilerTownUIVO.setAddress(controlObject.getBoiler().getAddress());

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
				boilerTownUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				boilerTownUIVO.setParamStatus("Показания в рамках допустимых пределов");
			} else {
				boilerTownUIVO.setParamStatusId(PARAM_STATUS_RED);
				boilerTownUIVO.setParamStatus("Показания вышли за допустимые пределы");
			}
		} else {
			boilerTownUIVO.setParamStatus("Снятие показаний не производится");
			boilerTownUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
		}
		boilersUIVO.add(boilerTownUIVO);
		return boilersUIVO;
	}

	public BoilerTownUIVO getTownById(Integer id) {
		Town town = townRepository.findOne(id);
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();
		townUIVO.setName(town.getRu_city());
		return townUIVO;
	}

}
