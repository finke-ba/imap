package com.imap.domain.jpa;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class ActualParamValuesPK implements Serializable {

	private Integer idControlObject;

	private Integer idParamDescription;

	public ActualParamValuesPK() {
	}
}
