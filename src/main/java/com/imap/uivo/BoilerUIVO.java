package com.imap.uivo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Объект-представление для данных о проверенной котельной.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class BoilerUIVO extends UIVO {

	private String paramName;

	private String paramValue;

	private String date;

	private String boilerName;

}
