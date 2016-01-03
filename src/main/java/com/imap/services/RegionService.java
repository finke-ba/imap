package com.imap.services;

import com.imap.uivo.RegionUIVO;

import java.util.List;

/**
 * Интерфейс для проверки и получения информации о всех котельных во всех городах в регионе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface RegionService {

	/**
	 * Получает список проверенных городов в текущем регионе.
	 *
	 * @return список проверенных городов
	 */
	List<RegionUIVO> getBoilersForRegionChecked();

}
