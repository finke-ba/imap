package com.imap.services.impl;

import com.imap.dao.UserDao;
import com.imap.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с данными пользователей.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class UserDetailsServiceStub implements UserDetailsService {

	private final UserDao userDao;

	@Autowired
	public UserDetailsServiceStub (final UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

		if ("user".equals(username)) {
			return userDao.getUser (username);
		}
		throw new UsernameNotFoundException ("User with name = " + username + " not found");
	}
}
