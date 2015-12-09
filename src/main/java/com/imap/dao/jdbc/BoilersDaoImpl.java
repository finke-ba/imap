package com.imap.dao.jdbc;

import com.imap.dao.BoilerTownDao;
import com.imap.dao.BoilersDao;
import com.imap.domain.jdbc.Boiler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilersDaoImpl extends AbstractDao implements BoilersDao, RowMapper<Boiler> {

	private static final String SQL_GET_BOILERS_FOR_TOWN =
			" SELECT t.ru_city, tj.*" +
			" FROM towns t" +
			" JOIN" +
			" (" +
				" SELECT b.town, b.address, b.name, b.ID as Boiler_id_left, sj.*" +
				" FROM boiler b" +
				" LEFT JOIN" +
				" (" +
					" SELECT s.Boiler_ID, apvj.*" +
					" FROM sensors s" +
					" JOIN" +
					" (" +
						" SELECT apv.Par_Name, apv.Par_Value, apv.Date_Time, apv.ID_CO" +
						" FROM actual_param_values apv" +
					" ) AS apvj" +
					" ON s.ID_CO = apvj.ID_CO" +
				" ) AS sj" +
				" ON b.ID = sj.Boiler_ID" +
				" WHERE Par_Name='T01' OR Par_Name='T02' OR Par_Name='T03' OR Par_Name IS NULL" +
			" )" +
			" AS tj" +
			" ON tj.town = t.id" +
			" ORDER BY town, Boiler_ID, ID_CO, Par_Name" +
			";";

	@Override
	public Map<Integer, Map<Integer, Boiler>> getBoilers() {
//		Map<Integer, Map<Integer, Boiler>> boilersMap =
		List<Boiler> query = getJdbcTemplate().query(SQL_GET_BOILERS_FOR_TOWN, this);
		return null;
	}

	@Override
	public Boiler mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<Integer, Map<Integer, Boiler>> map = new HashMap<>();
		return null;
	}
}
