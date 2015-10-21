package com.imap.dao;

import com.imap.domain.jdbc.BoilerTown;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerTownDao {

	List<BoilerTown> getBoilers(Integer id);

}
