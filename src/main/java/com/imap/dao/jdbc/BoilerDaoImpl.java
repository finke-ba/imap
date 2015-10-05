package com.imap.dao.jdbc;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerDaoImpl extends AbstractDao implements BoilerDao, RowMapper<Boiler> {

	private static final String SQL_GET_BOILERS = "SELECT" +
			" tw.id," +
			" tw.city" +
			" FROM" +
			" towns tw" +
			" WHERE" +
			" tw.id =?;";

	public Boiler getBoiler(Integer id) {
		return getJdbcTemplate().queryForObject(SQL_GET_BOILERS, this, id);
	}

	public Boiler mapRow(ResultSet rs, int rowNum) throws SQLException {
		Boiler boiler = new Boiler();
		boiler.setId(rs.getInt("id"));
		boiler.setName(rs.getString("city"));
		return boiler;
	}
}
