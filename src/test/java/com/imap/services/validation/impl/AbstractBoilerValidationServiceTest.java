package com.imap.services.validation.impl;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import com.imap.services.BoilerMapService;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class AbstractBoilerValidationServiceTest {

	@Mock
	protected BoilerMapService boilerMapService;

	@BeforeMethod
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	public HashMap<Integer, Map<Integer, Boiler>> getTownMap() {
		Boiler boiler1 = new Boiler();
		LinkedHashSet<ControlObject> controlObjects1 = new LinkedHashSet<>();
		boiler1.setBoilerId(1);
		boiler1.setBoilerName("Котельная №1");
		boiler1.setTownName("Аксай");
		boiler1.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		boiler1.setControlObjects(controlObjects1);
		ControlObject co11 = new ControlObject();
		co11.setParamName("T01");
		co11.setParamValue(58.00);
		co11.setDate(new Timestamp(1L));
		co11.setId(63);
		ControlObject co12 = new ControlObject();
		co12.setParamName("T02");
		co12.setParamValue(45.13);
		co12.setDate(new Timestamp(1L));
		co12.setId(63);
		ControlObject co13 = new ControlObject();
		co13.setParamName("T03");
		co13.setParamValue(11.80);
		co13.setDate(new Timestamp(1L));
		co13.setId(63);
		controlObjects1.add(co11);
		controlObjects1.add(co12);
		controlObjects1.add(co13);

		return new HashMap<Integer, Map<Integer, Boiler>>() {{
			put(1, new HashMap<Integer, Boiler>() {{
				put(1, boiler1);
			}});
		}};
	}

	public HashMap<Integer, Integer> getBoilerTownMap() {
		return new HashMap<Integer, Integer>() {{
			put(1, 1);
		}};
	}

	public Map<Integer, List<TownUIVO>> getTownCheckedMap() {
		List<TownUIVO> townList = new ArrayList<>();
		TownUIVO townUIVO = new TownUIVO();
		townList.add(townUIVO);
		townUIVO.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		townUIVO.setBoilerId(1);
		townUIVO.setBoilerName("Котельная №1");
		townUIVO.setParamStatus("Показания вышли за допустимые пределы");
		townUIVO.setParamStatusId(AbstractBoilerValidationService.PARAM_STATUS_RED);

		return new HashMap<Integer, List<TownUIVO>>() {{
			put(1, townList);
		}};
	}

}