package com.imap.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@Entity
@Table(name = "TOWNS")
public class Town implements Serializable {

	@Id
	private Integer id;

	private String city;

	private String ru_city;

	private Integer coord_x;

	private Integer coord_y;

	private Integer coord_x_name;

	private Integer coord_y_name;
}
