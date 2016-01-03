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
public class TownServiceImplTest extends AbstractBoilerTest {

	@InjectMocks
	private TownServiceImpl boilerTownService;

	@Test
	public void testGetTown() throws Exception {
		TownUIVO townUIVOActual = new TownUIVO();
		townUIVOActual.setTownName(getTownMap().get(1).get(1).getTownName());

		TownUIVO townUIVOExpected = boilerTownService.getTown(1);

		assertEquals(townUIVOActual.getTownName(), townUIVOExpected.getTownName());
	}

	@Test
	public void testGetBoilersInTownChecked() throws Exception {
		List<TownUIVO> boilersInTownCheckedActual = new ArrayList<>();

		TownUIVO townUIVO1 = new TownUIVO();
		boilersInTownCheckedActual.add(townUIVO1);
		townUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO1.setBoilerId(1);
		townUIVO1.setBoilerName("Котельная №1");
		townUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<TownUIVO> boilersInTownCheckedExpected = boilerTownService.getTownChecked(1);

		assertEquals(boilersInTownCheckedActual.get(0).getAddress(), boilersInTownCheckedExpected.get(0).getAddress());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerId(), boilersInTownCheckedExpected.get(0).getBoilerId());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerName(), boilersInTownCheckedExpected.get(0).getBoilerName());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatus(), boilersInTownCheckedExpected.get(0).getParamStatus());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatusId(), boilersInTownCheckedExpected.get(0).getParamStatusId());
	}

	@Test
	public void testCheckTown() throws Exception {
		List<TownUIVO> boilersInTownCheckedActual = new ArrayList<>();

		TownUIVO townUIVO1 = new TownUIVO();
		boilersInTownCheckedActual.add(townUIVO1);
		townUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO1.setBoilerId(1);
		townUIVO1.setBoilerName("Котельная №1");
		townUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<TownUIVO> boilersInTownCheckedExpected = boilerTownService.getTownChecked(getTownMap().get(1));

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

		TownUIVO townUIVOExpected = boilerTownService.mapControlObject(co, uivo);

		assertEquals(boilerUIVOActual.getParamStatusId(), townUIVOExpected.getParamStatusId());
		assertEquals(boilerUIVOActual.getParamStatus(), townUIVOExpected.getParamStatus());
	}

	@Test
	public void testCheckBoiler() throws Exception {
		List<TownUIVO> boilerUIVOsActual = new ArrayList<>();

		TownUIVO townUIVOActual1 = new TownUIVO();
		boilerUIVOsActual.add(townUIVOActual1);
		townUIVOActual1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		townUIVOActual1.setParamStatus("Предел 52.386");

		TownUIVO townUIVOActual2 = new TownUIVO();
		boilerUIVOsActual.add(townUIVOActual2);
		townUIVOActual2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);
		townUIVOActual2.setParamStatus("Предел 36.386");

		TownUIVO townUIVOActual3 = new TownUIVO();
		boilerUIVOsActual.add(townUIVOActual3);
		townUIVOActual3.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);
		townUIVOActual3.setParamStatus("Показания вышли за пределы +/- 10°C 41.386");

		Boiler boiler = getTownMap().get(1).get(1);
		List<TownUIVO> townUIVOsExpected = boilerTownService.checkBoiler(boiler.getControlObjects());

		assertEquals(boilerUIVOsActual.get(0).getParamStatusId(), townUIVOsExpected.get(0).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(0).getParamStatus(), townUIVOsExpected.get(0).getParamStatus());

		assertEquals(boilerUIVOsActual.get(1).getParamStatusId(), townUIVOsExpected.get(1).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(1).getParamStatus(), townUIVOsExpected.get(1).getParamStatus());

		assertEquals(boilerUIVOsActual.get(2).getParamStatusId(), townUIVOsExpected.get(2).getParamStatusId());
		assertEquals(boilerUIVOsActual.get(2).getParamStatus(), townUIVOsExpected.get(2).getParamStatus());
	}

	@Test
	public void testCheckAddedUIVORed() throws Exception {
		TownUIVO townUIVOActual = new TownUIVO();
		townUIVOActual.setParamStatus("Показания вышли за допустимые пределы");
		townUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<TownUIVO> townUIVOs = new ArrayList<>();
		TownUIVO townUIVO1 = new TownUIVO();
		townUIVOs.add(townUIVO1);
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		TownUIVO townUIVO2 = new TownUIVO();
		townUIVOs.add(townUIVO2);
		townUIVO2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);

		TownUIVO townUIVO3 = new TownUIVO();
		townUIVOs.add(townUIVO3);
		townUIVO3.setParamStatusId(AbstractBoilerService.PARAM_STATUS_YELLOW);

		TownUIVO townUIVOExpected = boilerTownService.mapCheckedTown(new TownUIVO(), townUIVOs);

		assertEquals(townUIVOActual.getParamStatus(), townUIVOExpected.getParamStatus());
		assertEquals(townUIVOActual.getParamStatusId(), townUIVOExpected.getParamStatusId());

	}

	@Test
	public void testCheckAddedUIVOGreen() throws Exception {
		TownUIVO townUIVOActual = new TownUIVO();
		townUIVOActual.setParamStatus("Показания в рамках допустимых пределов");
		townUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);

		List<TownUIVO> townUIVOs = new ArrayList<>();
		TownUIVO townUIVO1 = new TownUIVO();
		townUIVOs.add(townUIVO1);
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_GREEN);

		TownUIVO townUIVO2 = new TownUIVO();
		townUIVOs.add(townUIVO2);
		townUIVO2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_YELLOW);

		TownUIVO townUIVOExpected = boilerTownService.mapCheckedTown(new TownUIVO(), townUIVOs);

		assertEquals(townUIVOActual.getParamStatus(), townUIVOExpected.getParamStatus());
		assertEquals(townUIVOActual.getParamStatusId(), townUIVOExpected.getParamStatusId());
	}

	@Test
	public void testCheckAddedUIVOYellow() throws Exception {
		TownUIVO townUIVOActual = new TownUIVO();
		townUIVOActual.setParamStatus("Снятие показаний не производится");
		townUIVOActual.setParamStatusId(AbstractBoilerService.PARAM_STATUS_YELLOW);

		List<TownUIVO> townUIVOs = new ArrayList<>();
		TownUIVO townUIVO1 = new TownUIVO();
		townUIVOs.add(townUIVO1);
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_YELLOW);

		TownUIVO townUIVO2 = new TownUIVO();
		townUIVOs.add(townUIVO2);
		townUIVO2.setParamStatusId(AbstractBoilerService.PARAM_STATUS_YELLOW);

		TownUIVO townUIVOExpected = boilerTownService.mapCheckedTown(new TownUIVO(), townUIVOs);

		assertEquals(townUIVOActual.getParamStatus(), townUIVOExpected.getParamStatus());
		assertEquals(townUIVOActual.getParamStatusId(), townUIVOExpected.getParamStatusId());

		TownUIVO townUIVOExpected2 = boilerTownService.mapCheckedTown(new TownUIVO(), new ArrayList<>());
		assertEquals(townUIVOActual.getParamStatus(), townUIVOExpected2.getParamStatus());
		assertEquals(townUIVOActual.getParamStatusId(), townUIVOExpected2.getParamStatusId());

	}

}