package com.imap.dao.impl;

import com.imap.dao.BoilerInfoDao;
import com.imap.uivo.TownUIVO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для получаения информации о котельной.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class BoilerInfoDaoImpl extends AbstractDao implements BoilerInfoDao, RowMapper<TownUIVO> {

	/** Запрос для получения информации о котельной.*/
	private static final String SQL_GET_BOILERS_INFO =
			" SELECT t.ID, t.ru_city, jb.*" +
			" FROM towns t" +
			" JOIN (" +
			" SELECT b.name, b.address, b.town" +
			" FROM boiler b" +
			" WHERE b.ID = ?" +
			" ) AS jb" +
			" ON t.ID = jb.town;";

	@Override
	public TownUIVO getBoilerInfo(int id) {
		return getJdbcTemplate().queryForObject(SQL_GET_BOILERS_INFO, this, id);
	}

	@Override
	public TownUIVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TownUIVO result = new TownUIVO();
		result.setTownId(rs.getInt("town"));
		result.setTownName(rs.getString("ru_city"));
		result.setBoilerName(rs.getString("name"));
		result.setBoilerAddress(rs.getString("address"));
		return result;
	}

}
