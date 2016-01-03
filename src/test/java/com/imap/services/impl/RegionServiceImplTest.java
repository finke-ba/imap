package com.imap.services.impl;

import com.imap.services.AbstractBoilerService;
import com.imap.services.AbstractBoilerTest;
import com.imap.services.RegionService;
import com.imap.services.TownService;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class RegionServiceImplTest extends AbstractBoilerTest {

	@Spy
	protected TownServiceImpl boilerTownService;

	@InjectMocks
	private RegionServiceImpl boilerRegionService;

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();

		List<TownUIVO> townUIVOs = new ArrayList<>();
		TownUIVO townUIVO1 = new TownUIVO();
		townUIVOs.add(townUIVO1);
		townUIVO1.setAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO1.setBoilerId(1);
		townUIVO1.setBoilerName("Котельная №1");
		townUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO1.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		when(this.boilerTownService.getTownChecked(getTownMap().get(1))).thenReturn(townUIVOs);
	}

	@Test
	public void testGetBoilersForRegionCheck() throws Exception {
		List<RegionUIVO> boilersForRegionCheckActual = new ArrayList<>();
		RegionUIVO regionUIVO = new RegionUIVO();
		boilersForRegionCheckActual.add(regionUIVO);
		regionUIVO.setTownId(1);
		regionUIVO.setParamStatus("Показания вышли за допустимые пределы");
		regionUIVO.setParamStatusId(AbstractBoilerService.PARAM_STATUS_RED);

		List<RegionUIVO> boilersForRegionCheckExpected = boilerRegionService.getBoilersForRegionChecked();

		assertEquals(boilersForRegionCheckActual.get(0).getTownId(), boilersForRegionCheckExpected.get(0).getTownId());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatus(), boilersForRegionCheckExpected.get(0).getParamStatus());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatusId(), boilersForRegionCheckExpected.get(0).getParamStatusId());
	}
}