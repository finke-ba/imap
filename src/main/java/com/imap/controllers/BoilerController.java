package com.imap.controllers;

import com.imap.exceptions.NoSuchItemException;
import com.imap.services.BoilerService;
import com.imap.uivo.BoilerUIVO;
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
@RequestMapping("/imap/boiler")
public class BoilerController {

	@Autowired
	private BoilerService boilerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TownUIVO getBoiler(@PathVariable int id) throws NoSuchItemException {
		return boilerService.getBoiler(id);
	}

	@RequestMapping(value = "/{id}/check", method = RequestMethod.GET)
	public List<BoilerUIVO> getBoilerCheck(@PathVariable int id) throws NoSuchItemException {
		return boilerService.getBoilerChecked(id);
	}

}
