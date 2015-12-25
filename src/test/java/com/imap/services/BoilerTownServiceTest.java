package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.mockito.InjectMocks;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerTownServiceTest extends AbstractBoilerTest {

	@InjectMocks
	private BoilerTownService boilerTownService;

	@Test
	public void testGetTown() throws Exception {
		BoilerTownUIVO townUIVOActual = new BoilerTownUIVO();
		townUIVOActual.setTownName(getTownMap().get(1).get(1).getTownName());

		BoilerTownUIVO townUIVOExpected = boilerTownService.getTown(1);

		assertEquals(townUIVOActual.getTownName(), townUIVOExpected.getTownName());
	}

	@Test
	public void testGetBoilersInTownChecked() throws Exception {
		List<BoilerTownUIVO> boilersInTownCheckedActual = new ArrayList<>();

		BoilerTownUIVO boilerTownUIVO1 = new BoilerTownUIVO();
		boilersInTownCheckedActual.add(boilerTownUIVO1);
		boilerTownUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		boilerTownUIVO1.setBoilerId(1);
		boilerTownUIVO1.setBoilerName("Котельная №1");
		boilerTownUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		boilerTownUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<BoilerTownUIVO> boilersInTownCheckedExpected = boilerTownService.getBoilersInTownChecked(1);

		assertEquals(boilersInTownCheckedActual.get(0).getAddress(), boilersInTownCheckedExpected.get(0).getAddress());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerId(), boilersInTownCheckedExpected.get(0).getBoilerId());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerName(), boilersInTownCheckedExpected.get(0).getBoilerName());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatus(), boilersInTownCheckedExpected.get(0).getParamStatus());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatusId(), boilersInTownCheckedExpected.get(0).getParamStatusId());
	}

	@Test
	public void testCheckTown() throws Exception {
		List<BoilerTownUIVO> boilersInTownCheckedActual = new ArrayList<>();

		BoilerTownUIVO boilerTownUIVO1 = new BoilerTownUIVO();
		boilersInTownCheckedActual.add(boilerTownUIVO1);
		boilerTownUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		boilerTownUIVO1.setBoilerId(1);
		boilerTownUIVO1.setBoilerName("Котельная №1");
		boilerTownUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		boilerTownUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<BoilerTownUIVO> boilersInTownCheckedExpected = boilerTownService.checkTown(getTownMap().get(1));

		assertEquals(boilersInTownCheckedActual.get(0).getAddress(), boilersInTownCheckedExpected.get(0).getAddress());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerId(), boilersInTownCheckedExpected.get(0).getBoilerId());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerName(), boilersInTownCheckedExpected.get(0).getBoilerName());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatus(), boilersInTownCheckedExpected.get(0).getParamStatus());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatusId(), boilersInTownCheckedExpected.get(0).getParamStatusId());
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

		BoilerTownUIVO townUIVOExpected = boilerTownService.mapControlObject(co, uivo);

		assertEquals(boilerUIVOActual.getParamStatusId(), townUIVOExpected.getParamStatusId());
		assertEquals(boilerUIVOActual.getParamStatus(), townUIVOExpected.getParamStatus());
	}

	@Test
	public void testCheckBoiler() throws Exception {
		List<BoilerTownUIVO> boilerUIVOsActual = new ArrayList<>();

		BoilerTownUIVO boilerTownUIVOActual1 = new BoilerTownUIVO();
		boilerUIVOsActual.add(boilerTownUIVOActual1);
		boilerTownUIVOActual1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerTownUIVOActual1.setParamStatus("Предел 52.386");

		BoilerTownUIVO boilerTownUIVOActual2 = new BoilerTownUIVO();
		boilerUIVOsActual.add(boilerTownUIVOActual2);
		boilerTownUIVOActual2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		boilerTownUIVOActual2.setParamStatus("Предел 36.386");

		BoilerTownUIVO boilerTownUIVOActual3 = new BoilerTownUIVO();
		boilerUIVOsActual.add(boilerTownUIVOActual3);
		boilerTownUIVOActual3.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);
		boilerTownUIVOActual3.setParamStatus("Показания вышли за пределы +/- 10°C 41.386");

		Boiler boiler = getTownMap().get(1).get(1);
		List<BoilerTownUIVO> boilerTownUIVOsExpected = boilerTownService.checkBoiler(boiler.getControlObjects());

		assertEquals(boilerUIVOsActual.get(0).getParamStatusId(), boilerTownUIVOsExpected.get(0).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(0).getParamStatus(), boilerTownUIVOsExpected.get(0).getParamStatus());

		assertEquals(boilerUIVOsActual.get(1).getParamStatusId(), boilerTownUIVOsExpected.get(1).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(1).getParamStatus(), boilerTownUIVOsExpected.get(1).getParamStatus());

		assertEquals(boilerUIVOsActual.get(2).getParamStatusId(), boilerTownUIVOsExpected.get(2).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(2).getParamStatus(), boilerTownUIVOsExpected.get(2).getParamStatus());
	}

}