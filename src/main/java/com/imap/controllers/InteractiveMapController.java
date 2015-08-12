package com.imap.controllers;

import com.imap.dto.ResponseBoiler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Boris Finkelshtein.
 */
@RestController
@RequestMapping("/imap")
public class InteractiveMapController {

	@RequestMapping(value = "/boiler", method = RequestMethod.GET)
	public ResponseBoiler getBoiler() {
		return new ResponseBoiler("Boiler");
	}
}
