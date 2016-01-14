package com.imap.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Объект, содержащих полную информацию о котельных в регионе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class BoilerRegion {

	private Integer townId;

	private String townName;

	private Integer boilerId;

	private String boilerName;

	private String boilerAddress;

	private Integer paramId;

	private String paramName;

	private Double paramValue;

	private Timestamp paramDate;
}
