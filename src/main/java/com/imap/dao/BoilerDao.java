package com.imap.dao;

import com.imap.domain.Boiler;
import com.imap.domain.BoilerRegion;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс доступа к данным по котельным.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerDao {

	/**
	 * Получает список данных по всех котельных в регионе.
	 *
	 * @return список данных по всех котельных в регионе
	 */
	List<BoilerRegion> getBoilerRegion();

}
