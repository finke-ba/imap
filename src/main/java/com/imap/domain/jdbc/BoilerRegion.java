package com.imap.domain.jdbc;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BoilerRegion implements Serializable {

	private Integer id;

	private Integer status;

	public Integer getId() {
		return id;
	}

}
