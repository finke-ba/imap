package com.imap.dao.jdbc;

import com.imap.dao.BoilerRegionDao;
import com.imap.domain.jdbc.BoilerRegion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerRegionDaoImpl extends AbstractDao implements BoilerRegionDao, RowMapper<BoilerRegion> {

	public List<BoilerRegion> getBoilers() {
		return null;
	}

	public BoilerRegion mapRow(ResultSet resultSet, int i) throws SQLException {
		return null;
	}

}
