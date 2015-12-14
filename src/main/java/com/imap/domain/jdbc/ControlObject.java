package com.imap.domain.jdbc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class ControlObject implements Serializable {

	private Integer id;

	private String paramName;

	private Integer paramValue;

	private Date date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ControlObject that = (ControlObject) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (paramName != null ? !paramName.equals(that.paramName) : that.paramName != null) return false;
		if (paramValue != null ? !paramValue.equals(that.paramValue) : that.paramValue != null) return false;
		return !(date != null ? !date.equals(that.date) : that.date != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (paramName != null ? paramName.hashCode() : 0);
		result = 31 * result + (paramValue != null ? paramValue.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}
}
