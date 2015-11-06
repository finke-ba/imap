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
public class UIVO implements Serializable {

	private Integer paramStatusId;

	private String paramStatus;
}
