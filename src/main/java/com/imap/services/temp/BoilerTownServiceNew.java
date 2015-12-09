package com.imap.services.temp;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.ControlObject;
import com.imap.domain.jpa.Town;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerTownServiceNew extends AbstractBoilerServiceNew<BoilerTownUIVO> {

	@Autowired
	protected TownRepository townRepository;

	public List<BoilerTownUIVO> getBoilersInTownChecked(int id) {
		return checkTown(controlObjectRepository.findByTownId(id));
	}

	//Для одного города получаем котельные
	public List<BoilerTownUIVO> checkTown(List<ControlObject> controlObjects) {
		List<BoilerTownUIVO> boilerTownUIVOs = new ArrayList<>();

		List<ControlObject> boilerControlObjects = new ArrayList<>();
		//Получить приборы учета на одной котельной
		if (!controlObjects.isEmpty()) {
			Integer boilerId = controlObjects.get(0).getBoiler().getId();
			Iterator<ControlObject> controlObjectIter = controlObjects.iterator();
			while (controlObjectIter.hasNext()) {
				ControlObject next = controlObjectIter.next();
				if (boilerId.equals(next.getBoiler().getId())) {
					boilerControlObjects.add(next);
					controlObjectIter.remove();
				} else {
					boilerTownUIVOs.add(addCheckedBoiler(checkBoiler(boilerControlObjects)));

					boilerControlObjects.clear();
					boilerId = next.getBoiler().getId();
					boilerControlObjects.add(next);
					controlObjectIter.remove();
				}
			}
			boilerTownUIVOs.add(addCheckedBoiler(checkBoiler(boilerControlObjects)));
		}

		return boilerTownUIVOs;
	}

	public BoilerTownUIVO addCheckedBoiler(List<BoilerTownUIVO> boilerTownUIVOs) {
		BoilerTownUIVO townUIVO = new BoilerTownUIVO();

		if(!boilerTownUIVOs.isEmpty()) {
			townUIVO.setBoilerId(boilerTownUIVOs.get(0).getBoilerId());
			townUIVO.setName(boilerTownUIVOs.get(0).getName());
			townUIVO.setAddress(boilerTownUIVOs.get(0).getAddress());

			//Приборы учета для одной котельной
			boolean isGreen = false;
			for (BoilerTownUIVO boilerTownUIVO : boilerTownUIVOs) {
				townUIVO.setParamStatusId(boilerTownUIVO.getParamStatusId());
				townUIVO.setParamStatus("Снятие показаний не производится");
				if(boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_RED)) {
					townUIVO.setParamStatus("Показания вышли за допустимые пределы");
					return townUIVO;
				}
				if(boilerTownUIVO.getParamStatusId().equals(PARAM_STATUS_GREEN)) {
					isGreen = true;
				}
			}
			if (isGreen) {
				townUIVO.setParamStatusId(PARAM_STATUS_GREEN);
				townUIVO.setParamStatus("Показания в рамках допустимых пределов");
			}
		}

		return townUIVO;
	}

	@Override
	public void mapControlObject(ControlObject controlObject, BoilerTownUIVO uivo) {
		uivo.setBoilerId(controlObject.getBoiler().getId());
		uivo.setName(controlObject.getBoiler().getName());
		uivo.setAddress(controlObject.getBoiler().getAddress());
	}

	@Override
	public BoilerTownUIVO mapParamValue(ActualParamValue actualParamValue, UIVO uivo) {
		BoilerTownUIVO boilerTownUIVO = new BoilerTownUIVO();
		mapUIVO(boilerTownUIVO, uivo);
		return boilerTownUIVO;
	}

}
