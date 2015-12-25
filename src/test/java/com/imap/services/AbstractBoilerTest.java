package com.imap.services;

import com.imap.dao.BoilersDao;
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
	protected BoilersDao boilersDao;

	@BeforeMethod
	public void setUp() throws Exception {
		HashMap<Integer, Map<Integer, Boiler>> townMap = getTownMap();
		HashMap<Integer, Integer> boilerTownMap = getBoilerTownMap();

		MockitoAnnotations.initMocks(this);

		when(this.boilersDao.getTownMap()).thenReturn(townMap);
		when(this.boilersDao.getBoilerTownMap()).thenReturn(boilerTownMap);
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

		Boiler boiler2 = new Boiler();
		LinkedHashSet<ControlObject> controlObjects2 = new LinkedHashSet<>();
		boiler2.setBoilerId(1);
		boiler2.setBoilerName("Котельная №1");
		boiler2.setTownName("Аксай");
		boiler2.setBoilerAddress("ул.Дружбы, 7-б г.Аксай");
		boiler2.setControlObjects(controlObjects2);
		ControlObject co21 = new ControlObject();
		co21.setParamName("T01");
		co21.setParamValue(58.00);
		co21.setDate(new Timestamp(1L));
		co21.setId(63);
		ControlObject co22 = new ControlObject();
		co22.setParamName("T01");
		co22.setParamValue(58.00);
		co22.setDate(new Timestamp(1L));
		co22.setId(63);
		ControlObject co23 = new ControlObject();
		co23.setParamName("T01");
		co23.setParamValue(58.00);
		co23.setDate(new Timestamp(1L));
		co23.setId(63);
		controlObjects2.add(co21);
		controlObjects2.add(co22);
		controlObjects2.add(co23);

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
