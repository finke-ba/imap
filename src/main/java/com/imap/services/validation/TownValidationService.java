package com.imap.services.validation;

import com.imap.domain.Boiler;
import com.imap.uivo.TownUIVO;

import java.util.List;
import java.util.Map;

/**
 * Интерфей для проверки и получения информации о всех котельных в определенном городе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface TownValidationService {

	/**
	 * Проверяет данные котельных во всех городах.
	 *
	 * @param townMap соотношение идентиикатора города к котельным в городе
	 * @return соотношение идентификатора города к списку с проверенными данными котельных в городе
	 */
	Map<Integer, List<TownUIVO>> getTownMapChecked(Map<Integer, Map<Integer, Boiler>> townMap);

	/**
	 * Проверяет все котельные в одном городе.
	 *
	 * @param controlObjects список котельных в городе
	 * @return список проверенных котельных в городе
	 */
	List<TownUIVO> getTownChecked(Map<Integer, Boiler> controlObjects);

	/**
	 * Преобразует список проверенных приборов учета в объект, содержащий данные проверки.
	 *
	 * @param townUIVO  данные о проверенной котельной
	 * @param townUIVOs список проверенных приборов учета
	 * @return данные о проверенной котельной
	 */
	TownUIVO mapCheckedTown(TownUIVO townUIVO, List<TownUIVO> townUIVOs);

}
