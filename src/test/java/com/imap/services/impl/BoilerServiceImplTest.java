package com.imap.services.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.AbstractBoilerService;
import com.imap.services.AbstractBoilerTest;
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
		townUIVOActual.setAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVOActual.setTownName("Аксай");
		townUIVOActual.setTownId(1);

		TownUIVO townUIVOExpected = boilerService.getBoiler(1);

		assertEquals(townUIVOActual.getBoilerName(), townUIVOExpected.getBoilerName());
		assertEquals(townUIVOActual.getAddress(), townUIVOExpected.getAddress());
		assertEquals(townUIVOActual.getTownName(), townUIVOExpected.getTownName());
		assertEquals(townUIVOActual.getTownId(), townUIVOExpected.getTownId());
	}

	@Test
	public void testGetBoilerChecked() throws Exception {
		BoilerUIVO boilerUIVOActual = new BoilerUIVO();
		boilerUIVOActual.setParamName("T01");
		boilerUIVOActual.setParamValue("58.0");
		boilerUIVOActual.setDate(new Timestamp(1L).toString());
		boilerUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerUIVOActual.setParamStatus("Предел 52.386");

		List<BoilerUIVO> boilerUIVOsExpected = boilerService.getBoilerChecked(1);
		BoilerUIVO boilerUIVOExpected = boilerUIVOsExpected.get(0);

		assertEquals(boilerUIVOActual.getParamName(), boilerUIVOExpected.getParamName());
		assertEquals(boilerUIVOActual.getParamValue(), boilerUIVOExpected.getParamValue());
		assertEquals(boilerUIVOActual.getDate(), boilerUIVOExpected.getDate());
		assertEquals(boilerUIVOActual.getBoilerName(), boilerUIVOExpected.getBoilerName());
		assertEquals(boilerUIVOActual.getParamStatusId(), boilerUIVOExpected.getParamStatusId());
		assertEquals(boilerUIVOActual.getParamStatus(), boilerUIVOExpected.getParamStatus());
	}

	@Test
	public void testMapControlObject() throws Exception {

		BoilerUIVO boilerUIVOActual = new BoilerUIVO();
		boilerUIVOActual.setParamName("T01");
		boilerUIVOActual.setParamValue("58.0");
		boilerUIVOActual.setDate(new Timestamp(1L).toString());
		boilerUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerUIVOActual.setParamStatus("Предел 52.386");

		ControlObject co = new ControlObject();
		co.setParamName("T01");
		co.setParamValue(58.00);
		co.setDate(new Timestamp(1L));
		UIVO uivo = new UIVO();
		uivo.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		uivo.setParamStatus("Предел 52.386");

		BoilerUIVO boilerUIVOExpected = boilerService.mapControlObject(co, uivo);

		assertEquals(boilerUIVOActual.getParamName(), boilerUIVOExpected.getParamName());
		assertEquals(boilerUIVOActual.getParamValue(), boilerUIVOExpected.getParamValue());
		assertEquals(boilerUIVOActual.getDate(), boilerUIVOExpected.getDate());
		assertEquals(boilerUIVOActual.getBoilerName(), boilerUIVOExpected.getBoilerName());
		assertEquals(boilerUIVOActual.getParamStatusId(), boilerUIVOExpected.getParamStatusId());
		assertEquals(boilerUIVOActual.getParamStatus(), boilerUIVOExpected.getParamStatus());
	}

	@Test
	public void testCheckBoiler() throws Exception {
		List<BoilerUIVO> boilerUIVOsActual = new ArrayList<>();

		BoilerUIVO boilerUIVOActual1 = new BoilerUIVO();
		boilerUIVOsActual.add(boilerUIVOActual1);
		boilerUIVOActual1.setParamName("T01");
		boilerUIVOActual1.setParamValue("58.0");
		boilerUIVOActual1.setDate(new Timestamp(1L).toString());
		boilerUIVOActual1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerUIVOActual1.setParamStatus("Предел 52.386");

		BoilerUIVO boilerUIVOActual2 = new BoilerUIVO();
		boilerUIVOsActual.add(boilerUIVOActual2);
		boilerUIVOActual2.setParamName("T02");
		boilerUIVOActual2.setParamValue("45.13");
		boilerUIVOActual2.setDate(new Timestamp(1L).toString());
		boilerUIVOActual2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerUIVOActual2.setParamStatus("Предел 36.386");

		BoilerUIVO boilerUIVOActual3 = new BoilerUIVO();
		boilerUIVOsActual.add(boilerUIVOActual3);
		boilerUIVOActual3.setParamName("T03");
		boilerUIVOActual3.setParamValue("11.8");
		boilerUIVOActual3.setDate(new Timestamp(1L).toString());
		boilerUIVOActual3.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);
		boilerUIVOActual3.setParamStatus("Показания вышли за пределы +/- 10°C 41.386");

		Boiler boiler = getTownMap().get(1).get(1);

		List<BoilerUIVO> boilerUIVOsExpected = boilerService.checkBoiler(boiler.getControlObjects());

		assertEquals(boilerUIVOsActual.get(0).getParamName(), boilerUIVOsExpected.get(0).getParamName());
		assertEquals(boilerUIVOsActual.get(0).getParamValue(), boilerUIVOsExpected.get(0).getParamValue());
		assertEquals(boilerUIVOsActual.get(0).getDate(), boilerUIVOsExpected.get(0).getDate());
		assertEquals(boilerUIVOsActual.get(0).getBoilerName(), boilerUIVOsExpected.get(0).getBoilerName());
		assertEquals(boilerUIVOsActual.get(0).getParamStatusId(), boilerUIVOsExpected.get(0).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(0).getParamStatus(), boilerUIVOsExpected.get(0).getParamStatus());

		assertEquals(boilerUIVOsActual.get(1).getParamName(), boilerUIVOsExpected.get(1).getParamName());
		assertEquals(boilerUIVOsActual.get(1).getParamValue(), boilerUIVOsExpected.get(1).getParamValue());
		assertEquals(boilerUIVOsActual.get(1).getDate(), boilerUIVOsExpected.get(1).getDate());
		assertEquals(boilerUIVOsActual.get(1).getBoilerName(), boilerUIVOsExpected.get(1).getBoilerName());
		assertEquals(boilerUIVOsActual.get(1).getParamStatusId(), boilerUIVOsExpected.get(1).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(1).getParamStatus(), boilerUIVOsExpected.get(1).getParamStatus());

		assertEquals(boilerUIVOsActual.get(2).getParamName(), boilerUIVOsExpected.get(2).getParamName());
		assertEquals(boilerUIVOsActual.get(2).getParamValue(), boilerUIVOsExpected.get(2).getParamValue());
		assertEquals(boilerUIVOsActual.get(2).getDate(), boilerUIVOsExpected.get(2).getDate());
		assertEquals(boilerUIVOsActual.get(2).getBoilerName(), boilerUIVOsExpected.get(2).getBoilerName());
		assertEquals(boilerUIVOsActual.get(2).getParamStatusId(), boilerUIVOsExpected.get(2).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(2).getParamStatus(), boilerUIVOsExpected.get(2).getParamStatus());
	}

}