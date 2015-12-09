package com.imap.services.temp;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerServiceNew extends AbstractBoilerServiceNew<BoilerUIVO> {

	public List<BoilerUIVO> getBoilerChecked(int id) {
		return checkBoiler(controlObjectRepository.findByBoilerId(id));
	}

	public void mapParamValue(ActualParamValue actualParamValue, BoilerUIVO uivo) {
		uivo.setParamName(actualParamValue.getParName());
		uivo.setParamValue(actualParamValue.getParValue());
		uivo.setDate(actualParamValue.getDateTime());
	}

	@Override
	public BoilerUIVO mapParamValue(ActualParamValue actualParamValue, UIVO uivo) {
		BoilerUIVO boilerUIVO = new BoilerUIVO();
		mapUIVO(boilerUIVO, uivo);
		boilerUIVO.setParamName(actualParamValue.getParName());
		boilerUIVO.setParamValue(actualParamValue.getParValue());
		boilerUIVO.setDate(actualParamValue.getDateTime());
		return boilerUIVO;
	}
}
