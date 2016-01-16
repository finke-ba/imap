package com.imap.dao;

import com.imap.uivo.TownUIVO;

/**
 * Интерфейс для получаения информации о городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface TownInfoDao {

	/**
	 * Получает информацию о городе.
	 *
	 * @param id идентификатор города
	 * @return информация о городе
	 */
	TownUIVO getTownInfo(Integer id);

}
