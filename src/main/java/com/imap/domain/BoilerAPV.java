package com.imap.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class BoilerAPV implements Serializable {

	private String boilerAddress;

	private String boilerName;

	private String townName;

	private LinkedHashSet<ControlObject> controlObjects = new LinkedHashSet<>();
}
