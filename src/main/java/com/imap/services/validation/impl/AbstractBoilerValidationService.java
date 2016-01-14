package com.imap.services.validation.impl;

import com.imap.domain.ControlObject;
import com.imap.services.BoilerMapService;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Абстрактный сервис для проверки данных приборов учета на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public abstract class AbstractBoilerValidationService<U extends UIVO> {

	/** Статус того, что данные удовлетворяют графику отопления. */
	public final static Integer PARAM_STATUS_GREEN = 1;

	/** Статус того, что сбора данных для данного объекта не происходит. */
	public final static Integer PARAM_STATUS_YELLOW = 2;

	/** Статус того, что данные не удовлетворяют графику отопления. */
	public final static Integer PARAM_STATUS_RED = 3;

	/** Константа, задающая значение предела для температурного параметра на температурном графике, заданом линейно. */
	public static final Double XT = 8.0;

	/**
	 * Константа, задающая значение предела для значения параметра Т01
	 * прибора учета на температурном графике, заданом линейно.
	 */
	public static final Double Y1 = 2.2294 * (8 - XT) + 52.386;

	/**
	 * Константа, задающая значение предела для значения параметра Т02
	 * прибора учета на температурном графике, заданом линейно.
	 */
	public static final Double Y2 = 0.7748 * (8 - XT) + 36.386;

	/**
	 * Константа, задающая значение предела для значения параметра Т03
	 * прибора учета на температурном графике, заданом линейно.
	 */
	public static final Double Y3 = 1.2294 * (8 - XT) + 41.386;

	/** Константа для хранения названия параметра Т01. */
	public static final String T1 = "T01";

	/** Константа для хранения названия параметра Т02. */
	public static final String T2 = "T02";

	/** Константа для хранения названия параметра Т03. */
	public static final String T3 = "T03";

	/**
	 * Проверяет список приборов учета для одной котельной.
	 *
	 * @param controlObjects список приборов учета для одной котельной
	 * @return проверенный список приборов учета для одной котельной
	 */
	public List<U> checkBoiler(LinkedHashSet<ControlObject> controlObjects) {
		List<U> boilerControlObjects = new ArrayList<>();
		for (ControlObject controlObject : controlObjects) {
			checkControlObject(controlObject, boilerControlObjects);
		}
		return boilerControlObjects;
	}

	/**
	 * Проверяет пробор учета на соответствие температурному графику, заданному линейно.
	 *
	 * @param controlObject        прибор учета
	 * @param boilerControlObjects объект дл хранения списка проверенных приборов учета
	 */
	private void checkControlObject(ControlObject controlObject, List<U> boilerControlObjects) {
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

	/**
	 * Проверяет значение параметра прибора учета на соответствие температурному графику, заданному линейно.
	 *
	 * @param paramValue    значение параметра прибора учета
	 * @param y             допутсимый предел значения параметра прибора учета
	 * @param controlObject прибор учета
	 * @return проверенное значение параметра прибора учета
	 */
	private U checkParamValue(Double paramValue, Double y, ControlObject controlObject) {
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

	/**
	 * Преобразует данные прибора учета в представление для клиента.
	 *
	 * @param controlObject прибор учета
	 * @param uivo          проверенные значения параметра прибора учета
	 * @return проверенные значения параметра прибора учета в представлении для клиента
	 */
	protected abstract U mapControlObject(ControlObject controlObject, UIVO uivo);

}
