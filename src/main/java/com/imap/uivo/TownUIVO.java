package com.imap.uivo;

import lombok.Getter;
import lombok.Setter;

/**
 * Объект-представление для данных о проверенных котельных в городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Getter
@Setter
public class TownUIVO extends UIVO {

	private String townName;

	private Integer townId;

	private String boilerName;

	private String address;

	private Integer boilerId;

}
