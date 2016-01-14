package com.imap.services.validation;

import com.imap.domain.Boiler;
import com.imap.uivo.BoilerUIVO;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для проверки и получения информации об определенных котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerValidationService {

	/**
	 * Проверяет данные приборов учета на всех котельных.
	 *
	 * @param boilerTownMap соотношение идентификатора котельной к идентификатору города
	 * @param townMap       соотношение идентиикатора города к котельным в городе
	 * @return соотношение идентификатора котельной к списку с проверенными данными котельной
	 */
	Map<Integer, List<BoilerUIVO>> getBoilerMapChecked(Map<Integer, Integer> boilerTownMap,
													   Map<Integer, Map<Integer, Boiler>> townMap);

}
