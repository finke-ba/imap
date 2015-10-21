package com.imap.dao.jdbc;

import com.imap.dao.BoilerDao;
import com.imap.domain.jdbc.Boiler;
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
			" s_apv.Par_Name, s_apv.Par_Value, s_apv.Date_Time" +
			" FROM" +
			" boiler b" +
			" JOIN" +
			" (" +
				" SELECT" +
				" s.ID_CO, s.Boiler_ID, apv.Par_Value, apv.Par_Name, apv.Date_Time" +
				" FROM" +
				" sensors s" +
				" JOIN" +
				" (" +
					" SELECT *" +
					" FROM actual_param_values" +
				" ) AS apv" +
				" ON s.ID_CO = apv.ID_CO" +
			" ) AS s_apv" +
			" ON b.ID = s_apv.Boiler_ID" +
			" WHERE Boiler_ID=? AND (Par_Name='T01' OR Par_Name='T02' OR Par_Name='T03')" +
			" ORDER BY" +
			" Boiler_ID, Date_Time DESC, ID_CO, Par_Name" +
			" LIMIT 3;";


	public List<Boiler> getBoiler(Integer id) {
		return getJdbcTemplate().query(SQL_GET_BOILERS, this, id);
	}

	public Boiler mapRow(ResultSet rs, int rowNum) throws SQLException {
		Boiler boiler = new Boiler();
		boiler.setParamName(rs.getString("Par_Name"));
		boiler.setParamValue(rs.getInt("Par_Value"));
		boiler.setDate(rs.getDate("Date_Time"));
		return boiler;
	}
}
