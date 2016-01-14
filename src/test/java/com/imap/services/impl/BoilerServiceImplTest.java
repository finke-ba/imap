package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.validation.impl.AbstractBoilerValidationService;
import com.imap.uivo.TownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.mockito.InjectMocks;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerServiceImplTest extends AbstractBoilerTest {

	@InjectMocks
	private BoilerServiceImpl boilerService;

	@Test
	public void testGetBoiler() throws Exception {
		TownUIVO townUIVOActual = new TownUIVO();
		townUIVOActual.setBoilerName("Котельная №1");
		townUIVOActual.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVOActual.setTownName("Аксай");
		townUIVOActual.setTownId(1);

		TownUIVO townUIVOExpected = boilerService.getBoiler(1);

		assertEquals(townUIVOActual.getBoilerName(), townUIVOExpected.getBoilerName());
		assertEquals(townUIVOActual.getBoilerAddress(), townUIVOExpected.getBoilerAddress());
		assertEquals(townUIVOActual.getTownName(), townUIVOExpected.getTownName());
		assertEquals(townUIVOActual.getTownId(), townUIVOExpected.getTownId());
	}

	@Test
	public void testGetBoilerChecked() throws Exception {
		BoilerUIVO boilerUIVOActual = new BoilerUIVO();
		boilerUIVOActual.setParamName("T01");
		boilerUIVOActual.setParamValue("58.0");
		boilerUIVOActual.setParamDate(new Timestamp(1L).toString());
		boilerUIVOActual.setParamStatusId(AbstractBoilerValidationService.PARAM_STATUS_GREEN);
		boilerUIVOActual.setParamStatus("Предел 52.386");

		List<BoilerUIVO> boilerUIVOsExpected = boilerService.getBoilerChecked(1);
		BoilerUIVO boilerUIVOExpected = boilerUIVOsExpected.get(0);

		assertEquals(boilerUIVOActual.getParamName(), boilerUIVOExpected.getParamName());
		assertEquals(boilerUIVOActual.getParamValue(), boilerUIVOExpected.getParamValue());
		assertEquals(boilerUIVOActual.getParamDate(), boilerUIVOExpected.getParamDate());
		assertEquals(boilerUIVOActual.getBoilerName(), boilerUIVOExpected.getBoilerName());
		assertEquals(boilerUIVOActual.getParamStatusId(), boilerUIVOExpected.getParamStatusId());
		assertEquals(boilerUIVOActual.getParamStatus(), boilerUIVOExpected.getParamStatus());
	}

}