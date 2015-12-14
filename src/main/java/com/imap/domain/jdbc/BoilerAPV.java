package com.imap.domain.jdbc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

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
