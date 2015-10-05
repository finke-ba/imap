package com.imap.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BoilerTown implements Serializable {

	private String name;

	private String address;

	private String readings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
