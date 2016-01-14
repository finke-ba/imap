package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.uivo.BoilerUIVO;
import com.imap.uivo.RegionUIVO;
import com.imap.uivo.TownUIVO;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для получения информации о всех приборах учета на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerMapService {

	/**
	 * Возвращает данные обо всех приборах учета на котельных. Данные хранятся в виде карты, где
	 * Map<Integer(идентификатор города), Map<Integer(идентификатор котельной), Boiler(данные о котельной)>>.
	 *
	 * @return данные обо всех приборах учета на котельных
	 */
	Map<Integer, Map<Integer, Boiler>> getTownMap();

	/**
	 * Возвращет связку: идентификатор котельной - идентификатор города.
	 * Где Map<Integer(идентификатор котельной), Integer(идентификатор города)>.
	 *
	 * @return связка идентификатор котельной - идентификатор города
	 */
	Map<Integer, Integer> getBoilerTownMap();

	/**
	 * Возвращет связку:  идентификатор котельной - список проверенных приборов учета на одной котельной.
	 * Где Map<Integer(идентификатор котельной), List<BoilerUIVO>(список проверенных приборов учета)>.
	 *
	 * @return связка идентификатор котельной - список проверенных приборов учета
	 */
	Map<Integer, List<BoilerUIVO>> getBoilerChecked();

	/**
	 * Возвращет связку: идентификатор города - список проверенных котельных в определенном городе.
	 * Где Map<Integer(идентификатор города), List<TownUIVO>(список проверенных котельных)>.
	 *
	 * @return связка идентификатор котельной - список проверенных котельных
	 */
	Map<Integer, List<TownUIVO>> getTownChecked();

	/**
	 * Возвращает список с проверенными данными котельных в регионе.
	 *
	 * @return список с проверенными данными котельных в регионе
	 */
	List<RegionUIVO> getRegionChecked();

}
