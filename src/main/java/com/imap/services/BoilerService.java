package com.imap.services;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import com.imap.dto.ResponseBoiler;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public class BoilerService {

	private BoilerDao boilerDao;

	public List<Boiler> getBoilers() {
		return boilerDao.getBoilers();
	}

	public ResponseBoiler getBoiler(String boiler) {
		return new ResponseBoiler(boiler);
	}

	public void setBoilerDao(BoilerDao boilerDao) {
		this.boilerDao = boilerDao;
	}
}
