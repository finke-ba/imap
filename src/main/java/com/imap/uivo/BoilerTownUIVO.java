package com.imap.uivo;

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
public class BoilerTownUIVO implements Serializable {

	private String name;

	private String address;

	private Integer paramValue;

	private Integer boilerId;

	private String paramStatus;

	private Integer paramStatusId;

}
