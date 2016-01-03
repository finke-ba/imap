package com.imap.services;

import com.imap.uivo.TownUIVO;
import com.imap.uivo.BoilerUIVO;

import java.util.List;

/**
 * Интерфейс для проверки и получения информации об определенных котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerService {

	/**
	 * Получает информацию об определенной котельнной по её идентификатору.
	 *
	 * @param id - идентификатор котельной
	 * @return информация о котельной
	 */
	TownUIVO getBoiler(int id);

	/**
	 * Получает список проверенных приборов учета на одной котельной.
	 *
	 * @param boilerId - идентификатор котельной
	 * @return список проверенных приборов учета на котельной
	 */
	List<BoilerUIVO> getBoilerChecked(int boilerId);

}
