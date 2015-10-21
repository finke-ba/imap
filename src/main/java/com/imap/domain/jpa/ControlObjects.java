package com.imap.domain.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@Entity
@Table(name = "CONTROL_OBJECTS")
public class ControlObjects implements Serializable {

	@Id
	private Integer id;

	private String name;

	private String address;

	private String town;

	private String tel;

	@Column(name = "Dev_Model")
	private String devModel;

	@Temporal(TemporalType.DATE)
	private Date startDt;

	@Temporal(TemporalType.DATE)
	private Date stopDt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "SENSORS",
			joinColumns = @JoinColumn(name = "ID_CO"),
			inverseJoinColumns = @JoinColumn(name = "BOILER_ID"))
	private Boiler boiler;

	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "ACTUAL_PARAM_VALUES",
//			joinColumns = @JoinColumn(name = "ID_CO"))
	@JoinColumn(name = "ID_CO")
	private List<ActualParamValues> actualParamValues;
}
