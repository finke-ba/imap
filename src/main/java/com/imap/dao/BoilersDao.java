package com.imap.dao;

import com.imap.domain.jdbc.Boiler;

import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilersDao {

	Map<Integer, Map<Integer ,Boiler>> getBoilers();
}
