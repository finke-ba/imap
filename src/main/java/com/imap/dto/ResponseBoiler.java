package com.imap.dto;

import java.io.Serializable;

/**
 * Created by Boris Finkelshtein.
 */
public class ResponseBoiler implements Serializable{

	private String value;

	public ResponseBoiler(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
