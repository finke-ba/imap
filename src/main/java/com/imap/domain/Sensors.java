package com.imap.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@Entity
@Table(name = "SENSORS")
public class Sensors implements Serializable {

	@Id
	@JoinColumn(name = "BOILER")
	private Integer ID_CO;

	@JoinColumn(name = "CONTROL_OBJECTS")
	private Integer Boiler_ID;
}
