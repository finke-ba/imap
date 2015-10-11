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

	private Integer paramValue;

	private Integer boilerId;

	private String paramStatus;

	private Integer paramStatusId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getParamValue() {
		return paramValue;
	}

	public Integer getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(Integer boilerId) {
		this.boilerId = boilerId;
	}

	public void setParamValue(Integer paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamStatus() {
		return paramStatus;
	}

	public void setParamStatus(String paramStatus) {
		this.paramStatus = paramStatus;
	}

	public Integer getParamStatusId() {
		return paramStatusId;
	}

	public void setParamStatusId(Integer paramStatusId) {
		this.paramStatusId = paramStatusId;
	}
}
