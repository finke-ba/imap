package com.imap.dao.jdbc;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerDaoImpl extends AbstractDao implements BoilerDao, RowMapper<Boiler> {

	private static final String SQL_GET_BOILERS = "SELECT" +
			" tw.id," +
			" tw.city" +
			" FROM" +
			" towns tw;";

	public List<Boiler> getBoilers() {
		return getJdbcTemplate().query(SQL_GET_BOILERS, this);
	}

	public Boiler mapRow(ResultSet resultSet, int i) throws SQLException {
		Boiler boiler = new Boiler();
		boiler.setId(resultSet.getInt("id"));
		boiler.setName(resultSet.getString("city"));
		return boiler;
	}
}
