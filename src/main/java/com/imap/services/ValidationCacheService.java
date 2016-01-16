package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.exceptions.NoSuchItemException;
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
public interface ValidationCacheService {

	/**
	 * Возвращет список проверенных приборов учета на котельной.
	 *
	 * @return список проверенных приборов учета на котельной
	 */
	List<BoilerUIVO> getBoilerChecked(Integer boilerId) throws NoSuchItemException;

	/**
	 * Возвращет список проверенных котельных в городе.
	 *
	 * @return список проверенных котельных в городе
	 */
	List<TownUIVO> getTownChecked(Integer townId) throws NoSuchItemException;

	/**
	 * Возвращает список с проверенными данными котельных в регионе.
	 *
	 * @return список с проверенными данными котельных в регионе
	 */
	List<RegionUIVO> getRegionChecked() throws NoSuchItemException;

}
