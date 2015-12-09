package com.imap.services.temp;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.ControlObject;
import com.imap.repository.ControlObjectRepository;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public abstract class AbstractBoilerServiceNew<U extends UIVO> {

	public final static Integer PARAM_STATUS_GREEN = 1;

	public final static Integer PARAM_STATUS_YELLOW = 2;

	public final static Integer PARAM_STATUS_RED = 3;

	public static final Double XT = 8.0;

	public static final Double Y1 = 2.2294 * (8 - XT) + 52.386;

	public static final Double Y2 = 0.7748 * (8 - XT) + 36.386;

	public static final Double Y3 = 1.2294 * (8 - XT) + 41.386;

	public static final Integer ID_PD_T1 = 510;

	public static final Integer ID_PD_T2 = 515;

	public static final Integer ID_PD_T3 = 520;

	@Autowired
	protected ControlObjectRepository controlObjectRepository;

	//Для одной котельной получили список приборов учета
	public List<U> checkBoiler(List<ControlObject> controlObjects) {
		List<U> boilerControlObjects = new ArrayList<>();
		for (ControlObject controlObject : controlObjects) {
			checkControlObject(controlObject, boilerControlObjects);
		}
		return boilerControlObjects;
	}

	//Для одного прибора контроля учета
	public void checkControlObject(ControlObject controlObject, List<U> boilerControlObjects) {
		List<ActualParamValue> actualParamValues = controlObject.getActualParamValues();
		for (ActualParamValue actualParamValue : actualParamValues) {
			if (ID_PD_T1.equals(actualParamValue.getIdParamDescription())) {
				U uivo = checkParamValue(actualParamValue, Y1);
				mapControlObject(controlObject, uivo);
				boilerControlObjects.add(uivo);
			} else if (ID_PD_T2.equals(actualParamValue.getIdParamDescription())) {
				U uivo = checkParamValue(actualParamValue, Y2);
				mapControlObject(controlObject, uivo);
				boilerControlObjects.add(uivo);
			} else if (ID_PD_T3.equals(actualParamValue.getIdParamDescription())) {
				U uivo = checkParamValue(actualParamValue, Y3);
				mapControlObject(controlObject, uivo);
				boilerControlObjects.add(uivo);
			}
		}
		if (actualParamValues.isEmpty()) {
			U uivo = checkParamValue(null, Y1);
			mapControlObject(controlObject, uivo);
			boilerControlObjects.add(uivo);
		}
	}

	public void mapControlObject(ControlObject controlObject, U uivo) {

	}

	public U checkParamValue(ActualParamValue actualParamValue, Double y) {
		UIVO uivo = new UIVO();
		if(actualParamValue == null) {
			uivo.setParamStatusId(PARAM_STATUS_YELLOW);
			uivo.setParamStatus("Снятие показаний не производится");
		} else if ( Double.parseDouble(actualParamValue.getParValue()) >= (y - 10) &&
				Double.parseDouble(actualParamValue.getParValue()) <= (y + 10) ) {
			uivo.setParamStatusId(PARAM_STATUS_GREEN);
			uivo.setParamStatus(String.format("Предел %s", y.toString()));
		} else {
			uivo.setParamStatusId(PARAM_STATUS_RED);
			uivo.setParamStatus(String.format("Показания вышли за пределы +/- 10°C %s", y.toString()));
		}
		return mapParamValue(actualParamValue, uivo);
	}

	public abstract U mapParamValue(ActualParamValue actualParamValue, UIVO uivo);


	public void mapUIVO(U extendedUIVO, UIVO uivo) {
		extendedUIVO.setParamStatus(uivo.getParamStatus());
		extendedUIVO.setParamStatusId(uivo.getParamStatusId());
	}

}
