package com.imap.services;

import com.imap.dto.ResponseBoiler;

/**
 * Created by Boris Finkelshtein.
 */
public class BoilerService {

	public ResponseBoiler getBoiler(String boiler) {
		return new ResponseBoiler(boiler);
	}
}
