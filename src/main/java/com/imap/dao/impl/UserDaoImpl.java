package com.imap.dao.impl;

import com.imap.dao.UserDao;
import com.imap.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения информации о пользователях.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Repository
public class UserDaoImpl extends AbstractDao implements UserDao, RowMapper<User> {

  /** Запрос для получения данных о пользователе по имени пользователя.*/
  private static final String SQL_GET_USER =
      " SELECT u.username, u.password, u.authority" +
      " FROM users u" +
      " WHERE u.username=?;";

  @Override
  public User getUser (final String username) {
    return getJdbcTemplate().queryForObject(SQL_GET_USER, this, username);
  }

  @Override
  public User mapRow (final ResultSet rs, final int rowNum) throws SQLException {

    List<SimpleGrantedAuthority> auths = new ArrayList<> ();
    auths.add (new SimpleGrantedAuthority (rs.getString("authority")));

    User user = new User (
        rs.getString("username"),
        rs.getString("password"),
        auths
    );

    return user;
  }
}
