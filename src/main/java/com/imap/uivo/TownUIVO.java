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

	private Integer townId;

	private String townName;

	private Integer boilerId;

	private String boilerName;

	private String boilerAddress;

}
