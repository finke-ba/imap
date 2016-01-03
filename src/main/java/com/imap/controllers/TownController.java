package com.imap.controllers;

import com.imap.services.TownService;
import com.imap.uivo.TownUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для интерактивной карты телеметрии.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@RestController
@RequestMapping("/imap/town")
public class TownController {

	@Autowired
	private TownService boilerTownService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TownUIVO getTownNameNew(@PathVariable int id) {
		return boilerTownService.getTown(id);
	}

	@RequestMapping(value = "/{id}/check", method = RequestMethod.GET)
	public List<TownUIVO> getBoilersForTownCheck(@PathVariable int id) {
		return boilerTownService.getTownChecked(id);
	}

}
