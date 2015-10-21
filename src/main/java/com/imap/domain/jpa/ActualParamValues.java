package com.imap.domain.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@Entity
@Table(name = "ACTUAL_PARAM_VALUES")
@IdClass(ActualParamValuesPK.class)
public class ActualParamValues implements Serializable {

	@Id
	@Column(name = "ID_PD")
	private Integer idParamDescription;

	@Column(name = "Par_Value")
	private String parValue;

	@Column(name = "Date_Time")
	@Temporal(TemporalType.DATE)
	private Date dateTime;

	@Id
//	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "ID_CO")
	private Integer idControlObject;

	@Column(name = "Par_Name")
	private String parName;

	@Column(name = "Par_Dim")
	private String parDim;

	@Column(name = "Par_Num")
	private Integer parNum;
}
