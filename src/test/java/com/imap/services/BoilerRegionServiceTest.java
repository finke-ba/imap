package com.imap.services;

import com.imap.dao.BoilersDao;
import com.imap.domain.Boiler;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerRegionServiceTest extends AbstractBoilerTest{

	@Spy
	protected BoilerTownService boilerTownService;

	@InjectMocks
	private BoilerRegionService boilerRegionService;

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();

		List<BoilerTownUIVO> boilerTownUIVOs = new ArrayList<>();
		BoilerTownUIVO boilerTownUIVO1 = new BoilerTownUIVO();
		boilerTownUIVOs.add(boilerTownUIVO1);
		boilerTownUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		boilerTownUIVO1.setBoilerId(1);
		boilerTownUIVO1.setBoilerName("Котельная №1");
		boilerTownUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		boilerTownUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		when(this.boilerTownService.checkTown(getTownMap().get(1))).thenReturn(boilerTownUIVOs);
	}

	@Test
	public void testGetBoilersForRegionCheck() throws Exception {
		List<BoilerRegionUIVO> boilersForRegionCheckActual = new ArrayList<>();
		BoilerRegionUIVO boilerRegionUIVO = new BoilerRegionUIVO();
		boilersForRegionCheckActual.add(boilerRegionUIVO);
		boilerRegionUIVO.setTownId(1);
		boilerRegionUIVO.setParamStatus("Показания вышли за допустимые пределы");
		boilerRegionUIVO.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<BoilerRegionUIVO> boilersForRegionCheckExpected = boilerRegionService.getBoilersForRegionCheck();

		assertEquals(boilersForRegionCheckActual.get(0).getTownId(), boilersForRegionCheckExpected.get(0).getTownId());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatus(), boilersForRegionCheckExpected.get(0).getParamStatus());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatusId(), boilersForRegionCheckExpected.get(0).getParamStatusId());
	}
}