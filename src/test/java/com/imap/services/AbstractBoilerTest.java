package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.domain.ControlObject;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public abstract class AbstractBoilerTest {

	@Mock
	protected BoilerMapService boilerMapService;

	@BeforeMethod
	public void setUp() throws Exception {
		HashMap<Integer, Map<Integer, Boiler>> townMap = getTownMap();
		HashMap<Integer, Integer> boilerTownMap = getBoilerTownMap();

		MockitoAnnotations.initMocks(this);

		when(this.boilerMapService.getTownMap()).thenReturn(townMap);
		when(this.boilerMapService.getBoilerTownMap()).thenReturn(boilerTownMap);
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

		HashMap<Integer, Map<Integer, Boiler>> townMap = new HashMap<Integer, Map<Integer, Boiler>>() {{
			put(1, new HashMap<Integer, Boiler>() {{
				put(1, boiler1);
			}});
		}};

		return townMap;
	}

	public HashMap<Integer, Integer> getBoilerTownMap() {
		HashMap<Integer, Integer> boilerTownMap = new HashMap<Integer, Integer>() {{
			put(1, 1);
		}};
		return boilerTownMap;
	}

}
