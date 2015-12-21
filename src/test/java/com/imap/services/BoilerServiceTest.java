package com.imap.services;

import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerUIVO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerServiceTest {

	BoilerService boilerService = new BoilerService();

	@Test
	public void testBoiler() {

		Timestamp dateTime = new Timestamp(0L);

		ControlObject controlObject = new ControlObject();
		controlObject.setId(63);
		controlObject.setDate(dateTime);
		controlObject.setParamName("T01");
		controlObject.setParamValue(58.00);

		BoilerUIVO boilerUIVOActual = new BoilerUIVO();
		boilerUIVOActual.setParamName("T01");
		boilerUIVOActual.setParamValue("58.0");
		boilerUIVOActual.setDate(dateTime.toString());
		boilerUIVOActual.setParamStatus("Предел 52.386");
		boilerUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);

		BoilerUIVO boilerUIVOExpected = boilerService.checkParamValue(
				controlObject.getParamValue(),
				AbstractBoilerService.Y1,
				controlObject
		);

		Assert.assertEquals(boilerUIVOActual.getParamName(), boilerUIVOExpected.getParamName());
		Assert.assertEquals(boilerUIVOActual.getParamValue(), boilerUIVOExpected.getParamValue());
		Assert.assertEquals(boilerUIVOActual.getDate(), boilerUIVOExpected.getDate());
		Assert.assertEquals(boilerUIVOActual.getParamStatus(), boilerUIVOExpected.getParamStatus());
		Assert.assertEquals(boilerUIVOActual.getParamStatusId(), boilerUIVOExpected.getParamStatusId());
	}
}
