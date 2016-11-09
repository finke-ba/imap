package com.imap.uivo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UIVO implements Serializable {

	private Integer paramStatusId;

	private String paramStatus;
}
