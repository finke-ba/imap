package com.imap.services.temp;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.jdbc.BoilerAPV;
import com.imap.domain.jpa.Town;
import com.imap.repository.ControlObjectRepository;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.imap.services.BoilerTownService.*;

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
	private BoilerTownServiceNew boilerTownService;

	@Autowired
	private BoilersAPVDao boilersAPVDao;

	public List<BoilerRegionUIVO> getBoilersForRegionCheck() {

		Map<Integer, Map<Integer, BoilerAPV>> townMap = boilersAPVDao.getTownMap();
		townMap.get(0);

		List<BoilerRegionUIVO> regionUIVOs = new ArrayList<>();
		List<Town> towns = townRepository.findAll();
		regionUIVOs.addAll(towns.stream().map(
			town -> addCheckedTown(boilerTownService.checkTown(controlObjectRepository.findByTownId(town.getId())), town.getId())
		).collect(Collectors.toList()));
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
				if(townUIVO.getParamStatusId().equals(PARAM_STATUS_RED)) {
					regionUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return regionUIVO;
				}
				if(townUIVO.getParamStatusId().equals(PARAM_STATUS_GREEN)) {
					isGreen = true;
				}
			}
			if (isGreen) {
				regionUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				regionUIVO.setParamStatus("Показания в рамках допустимых пределов");
			}
		} else {
			regionUIVO.setParamStatusId(PARAM_STATUS_YELLOW);
			regionUIVO.setParamStatus("Снятие показаний не производится");
		}

		return regionUIVO;
	}
}
