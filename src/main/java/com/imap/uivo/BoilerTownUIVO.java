package com.imap.uivo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class BoilerTownUIVO extends UIVO {

	private String name;

	private String address;

	private Integer paramValue;

	private Integer boilerId;

}
