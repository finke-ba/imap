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
	public void testGetTownChecked() throws Exception {
		List<TownUIVO> boilersInTownCheckedActual = new ArrayList<>();

		TownUIVO townUIVO1 = new TownUIVO();
		boilersInTownCheckedActual.add(townUIVO1);
		townUIVO1.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO1.setBoilerId(1);
		townUIVO1.setBoilerName("Котельная №1");
		townUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO1.setParamStatusId(AbstractBoilerValidationService.PARAM_STATUS_RED);

		List<TownUIVO> boilersInTownCheckedExpected = boilerTownService.getTownChecked(1);

		assertEquals(boilersInTownCheckedActual.get(0).getBoilerAddress(), boilersInTownCheckedExpected.get(0).getBoilerAddress());
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
		townUIVO1.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO1.setBoilerId(1);
		townUIVO1.setBoilerName("Котельная №1");
		townUIVO1.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO1.setParamStatusId(AbstractBoilerValidationService.PARAM_STATUS_RED);

		List<TownUIVO> boilersInTownCheckedExpected = boilerTownService.getTownChecked(1);

		assertEquals(boilersInTownCheckedActual.get(0).getBoilerAddress(), boilersInTownCheckedExpected.get(0).getBoilerAddress());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerId(), boilersInTownCheckedExpected.get(0).getBoilerId());
		assertEquals(boilersInTownCheckedActual.get(0).getBoilerName(), boilersInTownCheckedExpected.get(0).getBoilerName());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatus(), boilersInTownCheckedExpected.get(0).getParamStatus());
		assertEquals(boilersInTownCheckedActual.get(0).getParamStatusId(), boilersInTownCheckedExpected.get(0).getParamStatusId());
	}

}