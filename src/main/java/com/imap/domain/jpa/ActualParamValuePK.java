package com.imap.domain.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode
public class ActualParamValuePK implements Serializable {

	private Integer idControlObject;

	private Integer idParamDescription;

	public ActualParamValuePK() {
	}
}
