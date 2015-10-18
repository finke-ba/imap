package com.imap.controllers;

import com.imap.domain.Boiler;
import com.imap.domain.BoilerNew;
import com.imap.domain.BoilerRegion;
import com.imap.domain.BoilerTown;
import com.imap.dto.ResponseBoiler;
import com.imap.services.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@RestController
@RequestMapping("/imap")
public class InteractiveMapController {

	@Autowired
	private BoilerService boilerService;

	@RequestMapping(value = "/get/boilers/region", method = RequestMethod.GET)
	public List<BoilerRegion> getBoilersForRegion() {
		return boilerService.getBoilersForRegion();
	}

	@RequestMapping(value = "/get/boilers/town", method = RequestMethod.GET)
	public List<BoilerTown> getBoilersForTown(@RequestParam int id) {
		return boilerService.getBoilersForTown(id);
	}

	@RequestMapping(value = "/get/boiler", method = RequestMethod.GET)
	public List<Boiler> getBoiler(@RequestParam int id) {
		return boilerService.getBoiler(id);
	}

	@RequestMapping(value = "/get/boilerNew", method = RequestMethod.GET)
	public List<BoilerNew> getBoilerNew(@RequestParam int id) {
		return boilerService.getBoilerNew(id);
	}

}
