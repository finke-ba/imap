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
	 * Проверяет данные приборов учета на котельной.
	 *
	 * @param boiler данные по котельной
	 * @return список с проверенными данными котельной
	 */
	List<BoilerUIVO> getBoilerChecked(Boiler boiler);

}
