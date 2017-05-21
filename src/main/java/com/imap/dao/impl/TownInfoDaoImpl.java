package com.imap.dao.impl;

import com.imap.dao.TownInfoDao;
import com.imap.uivo.TownUIVO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс для получаения информации о городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class TownInfoDaoImpl extends AbstractDao implements TownInfoDao, RowMapper<TownUIVO> {

	/** Запрос для получения информации о городе.*/
	private static final String SQL_GET_TOWN_INFO =
			" SELECT t.id, t.ru_city, t.coord_x, t.coord_y, t.coord_x_name, t.coord_y_name" +
			" FROM towns t" +
			" WHERE t.ID=?;";

	@Override
	public TownUIVO getTownInfo(Integer id) {
		return getJdbcTemplate().queryForObject(SQL_GET_TOWN_INFO, this, id);
	}

	@Override
	public TownUIVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TownUIVO result = new TownUIVO();
		result.setTownName(rs.getString("ru_city"));
		result.setTownId(rs.getInt("id"));
		result.setCoordX(rs.getString("coord_x"));
		result.setCoordY(rs.getString("coord_y"));
		result.setCoordXName(rs.getString("coord_x_name"));
		result.setCoordYName(rs.getString("coord_y_name"));
		return result;
	}
}
