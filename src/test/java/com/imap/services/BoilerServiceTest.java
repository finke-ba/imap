package com.imap.services;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.uivo.BoilerUIVO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerServiceTest {

	BoilerService boilerService = new BoilerService();

	@Test
	public void testBoiler() {
		Double xt = 8.0;
		Double y1 = 2.2294 * (8 - xt) + 52.386;
		Double Y2 = 0.7748 * (8 - xt) + 36.386;
		Double Y3 = 1.2294 * (8 - xt) + 41.386;

		Date date = new Date();

		ActualParamValue paramValue = new ActualParamValue();
		paramValue.setIdParamDescription(520);
		paramValue.setParValue("11.80");
		paramValue.setDateTime(date);
		paramValue.setIdControlObject(63);
		paramValue.setParName("T03");
		paramValue.setParDim("^C");
		paramValue.setParNum(156);

		Integer paramStatusGreen = 1;
		Integer paramStatusYellow = 2;
		Integer paramStatusRed = 3;

		BoilerUIVO boilerUIVOActual = new BoilerUIVO();
		boilerUIVOActual.setParamName("T03");
		boilerUIVOActual.setParamValue("11.80");
		boilerUIVOActual.setDate(date.toString());
		boilerUIVOActual.setParamStatus("Показания вышли за пределы +/- 10°C 52.386");
		boilerUIVOActual.setParamStatusId(paramStatusRed);

		BoilerUIVO boilerUIVOExpected = boilerService.checkParamValue(paramValue, y1);

		Assert.assertEquals(boilerUIVOActual.getParamName(), boilerUIVOExpected.getParamName());
		Assert.assertEquals(boilerUIVOActual.getParamValue(), boilerUIVOExpected.getParamValue());
		Assert.assertEquals(boilerUIVOActual.getDate(), boilerUIVOExpected.getDate());
		Assert.assertEquals(boilerUIVOActual.getParamStatus(), boilerUIVOExpected.getParamStatus());
		Assert.assertEquals(boilerUIVOActual.getParamStatusId(), boilerUIVOExpected.getParamStatusId());
	}
}
