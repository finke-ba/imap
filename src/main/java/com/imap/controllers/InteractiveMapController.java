package com.imap.controllers;

import com.imap.domain.jpa.Boiler;
import com.imap.domain.jdbc.BoilerRegion;
import com.imap.domain.jdbc.BoilerTown;
import com.imap.domain.jpa.ControlObjects;
import com.imap.services.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<com.imap.domain.jdbc.Boiler> getBoiler(@RequestParam int id) {
		return boilerService.getBoiler(id);
	}

	@RequestMapping(value = "/get/boiler/new", method = RequestMethod.GET)
	public List<ControlObjects> getBoilerNew(@RequestParam int id) {
		return boilerService.getBoilerNew(id);
	}

	@RequestMapping(value = "/get/boilers/town/new", method = RequestMethod.GET)
	public List<ControlObjects> getBoilersForTownNew(@RequestParam int id) {
		return boilerService.getBoilersForTownNew(id);
	}

	@RequestMapping(value = "/get/boilers/region/new", method = RequestMethod.GET)
	public List<ControlObjects> getBoilersForRegionNew() {
		return boilerService.getBoilersForRegionNew();
	}


}
