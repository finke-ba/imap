package com.imap.services;

import com.imap.domain.jpa.ActualParamValue;
import com.imap.domain.jpa.ControlObject;
import com.imap.repository.BoilerRepository;
import com.imap.repository.ControlObjectRepository;
import com.imap.repository.TownRepository;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.UIVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public abstract class AbstractBoilerService <U extends UIVO> {

	@Autowired
	protected BoilerRepository boilerRepository;

	@Autowired
	protected ControlObjectRepository controlObjectRepository;

	@Autowired
	protected TownRepository townRepository;

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


	public List<ControlObject> getBoilerCONew(int id) {
		return controlObjectRepository.findByBoilerId(id);
	}

	public List<ControlObject> getBoilersForTownNew(int id) {
		return controlObjectRepository.findByTownId(id);
	}

	public List<ControlObject> getBoilersForRegionNew() {
		return controlObjectRepository.findAll();
	}


	public List<List<U>> checkBoilerNew(List<ControlObject> controlObjectsForBoiler) {
		List<List<U>> boilerUIVOsList = new ArrayList<>();
		for (ControlObject controlObject : controlObjectsForBoiler) {
			boilerUIVOsList.add(checkBoilerControlObject(controlObject));
		}
		return boilerUIVOsList;
	}

	public abstract List<U> checkBoilerControlObject(ControlObject controlObject);

	public UIVO checkParamValue(ActualParamValue actualParamValue, Double y) {
		UIVO uivo = new UIVO();

		if ( Double.parseDouble(actualParamValue.getParValue()) >= (y - 10) &&
				Double.parseDouble(actualParamValue.getParValue()) <= (y + 10) ){
			uivo.setParamStatusId(PARAM_STATUS_GREEN);
			uivo.setParamStatus(String.format("Предел %s", y.toString()));
		} else {
			uivo.setParamStatusId(PARAM_STATUS_RED);
			uivo.setParamStatus(String.format("Показания вышли за пределы +/- 10°C %s", y.toString()));
		}

		return uivo;
	}
}
