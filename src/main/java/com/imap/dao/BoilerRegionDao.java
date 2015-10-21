package com.imap.dao;

import com.imap.domain.jdbc.BoilerRegion;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerRegionDao {

	List<BoilerRegion> getBoilers();

}
