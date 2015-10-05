package com.imap.services;

import com.imap.dao.BoilerDao;
import com.imap.domain.Boiler;
import com.imap.dto.ResponseBoiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class BoilerService {

	@Autowired
	private BoilerDao boilerDao;

	public List<Boiler> getBoilers() {
		return boilerDao.getBoilers();
	}

	public ResponseBoiler getBoiler(String boiler) {
		return new ResponseBoiler(boiler);
	}

}
