package com.imap.domain.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@Entity
@Table(name = "BOILER")
public class Boiler implements Serializable {

	@Id
	private Integer id;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TOWN")
	private Town town;

	private String address;

	private String tel;
}
