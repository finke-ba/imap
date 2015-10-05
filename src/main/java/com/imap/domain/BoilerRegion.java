package com.imap.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BoilerRegion implements Serializable {

	private Integer id;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
