package com.imap.controllers;

import com.imap.dto.ResponseBoiler;
import com.imap.services.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@RestController
@RequestMapping("/imap")
public class InteractiveMapController {

	@Autowired
	private BoilerService boilerService;

	@RequestMapping(value = "/boiler", method = RequestMethod.GET)
	public ResponseBoiler getBoiler() {
		return boilerService.getBoiler("Boiler");
	}

	@RequestMapping(value = "/boilers", method = RequestMethod.GET)
	public ResponseBoiler getBoilers() {
		return boilerService.getBoiler("Boilers");
	}

}
