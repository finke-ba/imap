package com.imap.dao.jdbc;

import com.imap.dao.BoilerTownDao;
import com.imap.domain.BoilerTown;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerTownDaoImpl extends AbstractDao implements BoilerTownDao, RowMapper<BoilerTown> {

	public List<BoilerTown> getBoilers(Integer id) {
		return null;
	}

	public BoilerTown mapRow(ResultSet resultSet, int i) throws SQLException {
		return null;
	}

}
