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
@Table(name = "BOILER")
public class BoilerNew implements Serializable {

	@Id
	private Integer id;

	private String name;

	@JoinColumn(name = "TOWNS")
	private Integer town;

	private String address;

	private String tel;
}
