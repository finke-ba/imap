package com.imap.dao.impl;

import com.imap.dao.BoilerDao;
import com.imap.domain.BoilerRegion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс для получаения информации о всех приборах учата на котельных.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerDaoImpl extends AbstractDao implements BoilerDao, RowMapper<BoilerRegion> {

	/**
	 * Запрос для получения данных о всех котельных в регионе.
	 */
	private static final String SQL_GET_BOILERS_FOR_REGION =
			" SELECT t.ru_city, t.ID AS town_id, tj.*" +
			" FROM towns t" +
			" LEFT JOIN" +
			" (" +
				" SELECT b.town AS town_id_right, b.address AS boiler_address, b.name AS boiler_name, b.ID AS boiler_id, sj.*" +
				" FROM boiler b" +
				" LEFT JOIN" +
				" (" +
					" SELECT s.Boiler_ID AS boiler_id_right, apvj.*" +
					" FROM sensors s" +
					" JOIN" +
					" (" +
						" SELECT apv.Par_Name AS param_name, apv.Par_Value AS param_value, apv.Date_Time AS param_date," +
						" apv.ID_CO AS param_id" +
						" FROM actual_param_values apv" +
					" ) AS apvj" +
					" ON s.ID_CO = apvj.param_id" +
				" ) AS sj" +
				" ON b.ID = sj.boiler_id_right" +
				" WHERE param_name='T01' OR param_name='T02' OR param_name='T03' OR param_name IS NULL" +
			" )" +
			" AS tj" +
			" ON tj.town_id_right = t.id" +
			" ORDER BY Town_ID, Boiler_ID, param_id, param_name" +
			";";

	@Override
	public List<BoilerRegion> getBoilerRegion() {
		return getJdbcTemplate().query(SQL_GET_BOILERS_FOR_REGION, this);
	}

	@Override
	public BoilerRegion mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoilerRegion result = new BoilerRegion();
		result.setTownId(rs.getInt("town_id"));
		result.setTownName(rs.getString("ru_city"));
		result.setBoilerId(rs.getInt("boiler_id"));
		result.setBoilerName(rs.getString("boiler_name"));
		result.setBoilerAddress(rs.getString("boiler_address"));
		result.setParamId(rs.getInt("param_id"));
		result.setParamName(rs.getString("param_name"));
		result.setParamValue(rs.getDouble("param_Value"));
		result.setParamDate(rs.getTimestamp("param_date"));
		return result;
	}

}
