package com.imap.dao;

import com.imap.uivo.TownUIVO;

/**
 * Интерфейс для получаения информации о котельной.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerInfoDao {

	/**
	 * Получает информацию о котельной.
	 *
	 * @param id идентификатор котельной
	 * @return информация о котельной
	 */
	TownUIVO getBoilerInfo(int id);

}
