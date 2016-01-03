package com.imap.services;

import com.imap.domain.Boiler;
import com.imap.uivo.TownUIVO;

import java.util.List;
import java.util.Map;

/**
 * Интерфей для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface TownService {

	/**
	 * Получает информацию об определенном городе по его идентификатору.
	 *
	 * @param id идентификатор города
	 * @return информацию городе
	 */
	TownUIVO getTown(Integer id);

	/**
	 * Получает список проверенных котельных в определенном городе.
	 *
	 * @param id идентификатор города
	 * @return список проверенных котельных
	 */
	List<TownUIVO> getTownChecked(int id);

	/**
	 * Проверяет все котельные в одном городе.
	 *
	 * @param controlObjects список котельных в городе
	 * @return список проверенных котельных в городе
	 */
	List<TownUIVO> getTownChecked(Map<Integer, Boiler> controlObjects);

	/**
	 * Преобразует список проверенных приборов учета в объект, содержащий данные проверки.
	 *
	 * @param townUIVO данные о проверенной котельной
	 * @param townUIVOs список проверенных приборов учета
	 * @return данные о проверенной котельной
	 */
	TownUIVO mapCheckedTown(TownUIVO townUIVO, List<TownUIVO> townUIVOs);

}
