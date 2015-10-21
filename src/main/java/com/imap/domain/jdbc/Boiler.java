package com.imap.domain.jdbc;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Boiler implements Serializable {

	private String paramName;

	private Integer paramValue;

	private Date date;

	private String reason;
}
