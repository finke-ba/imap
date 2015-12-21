package com.imap.uivo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Объект-представление для данных о проверке приборов учета.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UIVO implements Serializable {

	private Integer paramStatusId;

	private String paramStatus;
}
