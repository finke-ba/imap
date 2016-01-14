package com.imap.services;

import com.imap.exceptions.NoSuchItemException;
import com.imap.uivo.TownUIVO;

import java.util.List;

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
	TownUIVO getTown(Integer id) throws NoSuchItemException;

	/**
	 * Получает список проверенных котельных в определенном городе.
	 *
	 * @param id идентификатор города
	 * @return список проверенных котельных
	 */
	List<TownUIVO> getTownChecked(int id) throws NoSuchItemException;

}
