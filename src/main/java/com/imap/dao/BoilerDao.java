package com.imap.dao;

import com.imap.domain.jdbc.Boiler;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerDao {

	List<Boiler> getBoiler(Integer id);

}
