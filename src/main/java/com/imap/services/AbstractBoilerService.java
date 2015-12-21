package com.imap.services;

import com.imap.domain.ControlObject;
import com.imap.uivo.UIVO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public abstract class AbstractBoilerService<U extends UIVO> {

	public final static Integer PARAM_STATUS_GREEN = 1;

	public final static Integer PARAM_STATUS_YELLOW = 2;

	public final static Integer PARAM_STATUS_RED = 3;

	public static final Double XT = 8.0;

	public static final Double Y1 = 2.2294 * (8 - XT) + 52.386;

	public static final Double Y2 = 0.7748 * (8 - XT) + 36.386;

	public static final Double Y3 = 1.2294 * (8 - XT) + 41.386;

	public static final String T1 = "T01";

	public static final String T2 = "T02";

	public static final String T3 = "T03";

	//Для одной котельной получили список приборов учета
	public List<U> checkBoiler(LinkedHashSet<ControlObject> controlObjects) {
		List<U> boilerControlObjects = new ArrayList<>();
		for (ControlObject controlObject : controlObjects) {
			checkControlObject(controlObject, boilerControlObjects);
		}
		return boilerControlObjects;
	}

	//Для одного прибора контроля учета
	public void checkControlObject(ControlObject controlObject, List<U> boilerControlObjects) {
		if (T1.equals(controlObject.getParamName())) {
			U uivo = checkParamValue(controlObject.getParamValue(), Y1, controlObject);
			boilerControlObjects.add(uivo);
		} else if (T2.equals(controlObject.getParamName())) {
			U uivo = checkParamValue(controlObject.getParamValue(), Y2, controlObject);
			boilerControlObjects.add(uivo);
		} else if (T3.equals(controlObject.getParamName())) {
			U uivo = checkParamValue(controlObject.getParamValue(), Y3, controlObject);
			boilerControlObjects.add(uivo);
		}
	}

	public U checkParamValue(Double paramValue, Double y, ControlObject controlObject) {
		UIVO uivo = new UIVO();
		if (paramValue == null) {
			uivo.setParamStatusId(PARAM_STATUS_YELLOW);
			uivo.setParamStatus("Снятие показаний не производится");
		} else if (paramValue >= (y - 10) && paramValue <= (y + 10)) {
			uivo.setParamStatusId(PARAM_STATUS_GREEN);
			uivo.setParamStatus(String.format("Предел %s", y.toString()));
		} else {
			uivo.setParamStatusId(PARAM_STATUS_RED);
			uivo.setParamStatus(String.format("Показания вышли за пределы +/- 10°C %s", y.toString()));
		}
		return mapControlObject(controlObject, uivo);
	}

	public abstract U mapControlObject(ControlObject controlObject, UIVO uivo);

}
