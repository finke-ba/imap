package com.imap.services.validation;

import com.imap.domain.Boiler;
import com.imap.uivo.RegionUIVO;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для проверки и получения информации о всех котельных во всех городах в регионе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface RegionValidationService {

	/**
	 * Проверяет данные котельных в регионе.
	 *
	 * @return список с проверенными данными котельных в регионе
	 */
	List<RegionUIVO> getRegionChecked(Map<Integer, Map<Integer, Boiler>> townMap);

}
