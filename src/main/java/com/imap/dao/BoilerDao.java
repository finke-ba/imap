package com.imap.dao;

import com.imap.domain.Boiler;

import java.util.Map;

/**
 * Интерфейс доступа к данным по котельным в БД.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerDao {

	/**
	 * Обновляет данные о котельных.
	 */
	void updateBoilersMap();

	/**
	 * Получает данные о котельных.
	 *
	 * @return данные о котельных
	 */
	Map<Integer, Map<Integer, Boiler>> getTownMap();

	/**
	 * Получает соотношение идентификатора котельной к идентификатору города, в котором находится котельная.
	 *
	 * @return соотношение идентификатора котельной к идентификатору города
	 */
	Map<Integer, Integer> getBoilerTownMap();
}
