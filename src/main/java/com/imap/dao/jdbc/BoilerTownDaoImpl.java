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

	private static final String SQL_GET_BOILERS_FOR_TOWN = " SELECT t.ru_city, tj.*" +
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
			" WHERE town=? AND (Par_Name='T01' OR Par_Name='T02' OR Par_Name='T03' OR Par_Name IS NULL)" +
			" )" +
			" AS tj" +
			" ON tj.town = t.id" +
			" ORDER BY Boiler_ID, ID_CO, Par_Name" +
			";";

	public List<BoilerTown> getBoilers(Integer id) {
		return getJdbcTemplate().query(SQL_GET_BOILERS_FOR_TOWN, this, id);
	}

	public BoilerTown mapRow(ResultSet rs, int i) throws SQLException {
		BoilerTown boilerTown = new BoilerTown();
		boilerTown.setName(rs.getString("name"));
		boilerTown.setAddress(rs.getString("address"));
		boilerTown.setParamValue(rs.getInt("par_value"));
		boilerTown.setBoilerId(rs.getInt("boiler_id_left"));
		return boilerTown;
	}

}
