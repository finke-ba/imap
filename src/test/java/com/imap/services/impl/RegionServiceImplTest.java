package com.imap.services.impl;

import com.imap.services.validation.impl.AbstractBoilerValidationService;
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

	@InjectMocks
	private RegionServiceImpl boilerRegionService;

	@Test
	public void testGetBoilersForRegionCheck() throws Exception {
		List<RegionUIVO> boilersForRegionCheckActual = new ArrayList<>();
		RegionUIVO regionUIVO = new RegionUIVO();
		boilersForRegionCheckActual.add(regionUIVO);
		regionUIVO.setTownId(1);
		regionUIVO.setParamStatus("Показания вышли за допустимые пределы");
		regionUIVO.setParamStatusId(AbstractBoilerValidationService.PARAM_STATUS_RED);

		List<RegionUIVO> boilersForRegionCheckExpected = boilerRegionService.getBoilersForRegionChecked();

		assertEquals(boilersForRegionCheckActual.get(0).getTownId(), boilersForRegionCheckExpected.get(0).getTownId());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatus(), boilersForRegionCheckExpected.get(0).getParamStatus());
		assertEquals(boilersForRegionCheckActual.get(0).getParamStatusId(), boilersForRegionCheckExpected.get(0).getParamStatusId());
	}
}