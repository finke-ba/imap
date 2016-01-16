package com.imap.services.impl;

import com.imap.dao.BoilerInfoDao;
import com.imap.services.validation.impl.AbstractBoilerValidationService;
import com.imap.uivo.TownUIVO;
import com.imap.uivo.BoilerUIVO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerServiceImplTest extends AbstractBoilerTest {

	@Mock
	private BoilerInfoDao boilerInfoDao;

	@InjectMocks
	private BoilerServiceImpl boilerService;

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();

		TownUIVO townUIVO = new TownUIVO();
		townUIVO.setBoilerName("Котельная №1");
		townUIVO.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO.setTownName("Аксай");
		townUIVO.setTownId(1);

		when(this.boilerInfoDao.getBoilerInfo(1)).thenReturn(townUIVO);
	}

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