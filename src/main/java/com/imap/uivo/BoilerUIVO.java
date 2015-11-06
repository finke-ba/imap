package com.imap.uivo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class BoilerUIVO extends UIVO {

	private String paramName;

	private String paramValue;

	private Date date;

//	private String reason;
}
