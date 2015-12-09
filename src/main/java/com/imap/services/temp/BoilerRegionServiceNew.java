package com.imap.services.temp;

import com.imap.domain.jpa.Town;
import com.imap.repository.ControlObjectRepository;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerRegionServiceNew {

	@Autowired
	protected ControlObjectRepository controlObjectRepository;

	@Autowired
	protected TownRepository townRepository;

	@Autowired
	BoilerTownServiceNew boilerTownService;

	public List<BoilerRegionUIVO> getBoilersForRegionCheck() {
		List<BoilerRegionUIVO> regionUIVOs = new ArrayList<>();
		List<Town> towns = townRepository.findAll();
		for (Town town : towns) {
			regionUIVOs.add(addCheckedTown(boilerTownService.checkTown(controlObjectRepository.findByTownId(town.getId())), town.getId()));
		}
		return regionUIVOs;
	}

	private BoilerRegionUIVO addCheckedTown(List<BoilerTownUIVO> townUIVOs, Integer id) {
		BoilerRegionUIVO regionUIVO = new BoilerRegionUIVO();

		regionUIVO.setTownId(id);
		if(!townUIVOs.isEmpty()) {
			//Приборы учета для одной котельной
			boolean isGreen = false;
			for (BoilerTownUIVO townUIVO : townUIVOs) {
				regionUIVO.setParamStatusId(townUIVO.getParamStatusId());
				regionUIVO.setParamStatus("Снятие показаний не производится");
				if(townUIVO.getParamStatusId().equals(boilerTownService.PARAM_STATUS_RED)) {
					regionUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return regionUIVO;
				}
				if(townUIVO.getParamStatusId().equals(boilerTownService.PARAM_STATUS_GREEN)) {
					isGreen = true;
				}
			}
			if (isGreen) {
				regionUIVO.setParamStatusId(boilerTownService.PARAM_STATUS_GREEN);
				regionUIVO.setParamStatus("Показания в рамках допустимых пределов");
			}
		} else {
			regionUIVO.setParamStatusId(boilerTownService.PARAM_STATUS_YELLOW);
			regionUIVO.setParamStatus("Снятие показаний не производится");
		}

		return regionUIVO;
	}
}
