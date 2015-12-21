package com.imap.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Объект, содержащий информацию о котельной.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class Boiler implements Serializable {

	private Integer boilerId;

	private String boilerAddress;

	private String boilerName;

	private String townName;

	private LinkedHashSet<ControlObject> controlObjects = new LinkedHashSet<>();
}
