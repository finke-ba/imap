package com.imap.dao;

import com.imap.domain.User;

/**
 * Интерфейс доступа к данным по пользователям.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface UserDao {

  User getUser (String username);

}
