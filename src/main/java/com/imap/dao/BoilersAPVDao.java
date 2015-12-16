package com.imap.dao;

import com.imap.domain.jdbc.Boiler;
import com.imap.domain.jdbc.BoilerAPV;

import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilersAPVDao {

	void updateBoilersMap();

	Map<Integer,Map<Integer,BoilerAPV>> getTownMap();

	Map<Integer, Integer> getBoilerTownMap();
}
