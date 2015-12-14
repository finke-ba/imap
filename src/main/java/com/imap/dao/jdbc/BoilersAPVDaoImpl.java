package com.imap.dao.jdbc;

import com.imap.dao.BoilersAPVDao;
import com.imap.domain.jdbc.BoilerAPV;
import com.imap.domain.jdbc.ControlObject;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilersAPVDaoImpl extends AbstractDao implements BoilersAPVDao {

	//<TownId <BoilerId, Boiler>>
	private static final ConcurrentMap<Integer, ConcurrentMap<Integer, BoilerAPV>> townMap = new ConcurrentHashMap<>();

	private static final ConcurrentMap<Integer, Integer> boilerTownMap = new ConcurrentHashMap<>();

	private static final String SQL_GET_BOILERS_FOR_TOWN =
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
	public void updateBoilersMap() {
		CallbackHandler handler = new CallbackHandler();
		getJdbcTemplate().query(SQL_GET_BOILERS_FOR_TOWN, handler);
	}

	private static class CallbackHandler implements RowCallbackHandler {
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int boilerId = rs.getInt("boiler_id");
			int townId = rs.getInt("town_id");
			int controlObjectId = rs.getInt("param_id");

			BoilerAPV boiler = new BoilerAPV();
			boiler.setBoilerAddress(rs.getString("boiler_address"));
			boiler.setBoilerName(rs.getString("boiler_name"));
			boiler.setTownName(rs.getString("ru_city"));

			ControlObject controlObject = new ControlObject();
			controlObject.setId(controlObjectId);
			controlObject.setParamName(rs.getString("param_name"));
			controlObject.setParamValue(rs.getInt("param_Value"));
			controlObject.setDate(rs.getDate("param_date"));

			boilerTownMap.put(boilerId, townId);

			if (townMap.containsKey(townId)) {
				ConcurrentMap<Integer, BoilerAPV> boilerMap = townMap.get(townId);
				if (boilerMap.containsKey(boilerId)) {
					BoilerAPV boilerAPV = boilerMap.get(boilerId);
					//добавить в список
					if (!controlObject.getId().equals(0)) {
						boilerAPV.getControlObjects().add(controlObject);
					}
				} else {
					if (!controlObject.getId().equals(0)) {
						boiler.getControlObjects().add(controlObject);
					}
					boilerMap.put(boilerId, boiler);
				}
			} else {
				ConcurrentMap<Integer, BoilerAPV> boilerMap = new ConcurrentHashMap<>();
				if (!controlObject.getId().equals(0)) {
					boiler.getControlObjects().add(controlObject);
				}
				boilerMap.put(boilerId, boiler);
				townMap.put(townId, new ConcurrentHashMap<>(boilerMap));
			}
		}
	}

	public BoilersAPVDaoImpl() {
		System.out.println("Creating instance");
	}

	public Map<Integer, Map<Integer, BoilerAPV>> getTownMap() {
		return Collections.unmodifiableMap(townMap);
	}

	public Map<Integer, Integer> getBoilerTownMap() {
		return Collections.unmodifiableMap(boilerTownMap);
	}

}
