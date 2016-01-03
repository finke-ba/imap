package com.imap.services;

import com.imap.domain.Boiler;

import java.util.Map;

/**
 * Интерфейс для получения информации о всех приборах учета на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerMapService {

	/**
	 *  Обновляет данные о котельных.
	 */
	void updateBoilersMap();

	/**
	 * Возвращает данные обо всех приборах учета на котельных. Данные хранятся в виде карты, где
	 * Map<Integer(идентификатор города), Map<Integer(идентификатор котельной), Boiler(данные о котельной)>>.
	 *
	 * @return данные обо всех приборах учета на котельных
	 */
	Map<Integer, Map<Integer, Boiler>> getTownMap();

	/**
	 * Возвращет связку идентификатор котельной - идентификатор города, где
	 * Map<Integer(идентификатор котельной), Integer(идентификатор города)>.
	 *
	 * @return связку идентификатор котельной - идентификатор города
	 */
	Map<Integer, Integer> getBoilerTownMap();

}
