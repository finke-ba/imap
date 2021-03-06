package com.imap.services.validation.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.validation.BoilerValidationService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Сервис для проверки и получения информации об определенных котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerValidationServiceImpl extends AbstractBoilerValidationService<BoilerUIVO> implements BoilerValidationService {

	@Override
	public List<BoilerUIVO> getBoilerChecked(Boiler boiler) {
		return checkBoiler(boiler.getControlObjects());
	}

	@Override
	protected BoilerUIVO mapControlObject(ControlObject controlObject, UIVO uivo) {
		BoilerUIVO boilerUIVO = new BoilerUIVO();
		boilerUIVO.setParamStatus(uivo.getParamStatus());
		boilerUIVO.setParamStatusId(uivo.getParamStatusId());
		boilerUIVO.setParamName(controlObject.getParamName());
		boilerUIVO.setParamValue(controlObject.getParamValue().toString());
		boilerUIVO.setParamDate(controlObject.getDate().toString());
		return boilerUIVO;
	}

}
