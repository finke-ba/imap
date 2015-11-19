package com.imap.controllers;

import com.imap.domain.jdbc.BoilerRegion;
import com.imap.domain.jdbc.BoilerTown;
import com.imap.domain.jpa.Boiler;
import com.imap.domain.jpa.ControlObject;
import com.imap.services.BoilerRegionService;
import com.imap.services.BoilerService;
import com.imap.services.BoilerTownService;
import com.imap.uivo.BoilerRegionUIVO;
import com.imap.uivo.BoilerTownUIVO;
import com.imap.uivo.BoilerUIVO;
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

	@Autowired
	private BoilerTownService boilerTownService;

	@Autowired
	private BoilerRegionService boilerRegionService;

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

	@RequestMapping(value = "/get/boilerCO/new", method = RequestMethod.GET)
	public List<ControlObject> getBoilerCONew(@RequestParam int id) {
		return boilerService.getBoilerCONew(id);
	}

	@RequestMapping(value = "/get/boilers/town/new", method = RequestMethod.GET)
	public List<ControlObject> getBoilersForTownNew(@RequestParam int id) {
		return boilerService.getBoilersForTownNew(id);
	}

	@RequestMapping(value = "/get/boilers/region/new", method = RequestMethod.GET)
	public List<ControlObject> getBoilersForRegionNew() {
		return boilerService.getBoilersForRegionNew();
	}

	@RequestMapping(value = "/get/boiler/check/new", method = RequestMethod.GET)
	public List<List<BoilerUIVO>> getBoilerCheckNew(@RequestParam int id) {
		return boilerService.getBoilerCheckNew(id);
	}

	@RequestMapping(value = "/get/boilers/town/check/new", method = RequestMethod.GET)
	public List<List<BoilerTownUIVO>> getBoilersForTownCheckNew(@RequestParam int id) {
		return boilerTownService.getBoilersForTownCheckNew(id);
	}

	@RequestMapping(value = "/get/boilers/region/check/new", method = RequestMethod.GET)
	public List<BoilerRegionUIVO> getBoilersForRegionCheckNew() {
		return boilerRegionService.getBoilersForRegionCheckNew();
	}

	@RequestMapping(value = "/get/boilers/town/name/new", method = RequestMethod.GET)
	public BoilerTownUIVO getTownNameNew(@RequestParam int id) {
		return boilerRegionService.getTownById(id);
	}

	@RequestMapping(value = "/get/boiler/new", method = RequestMethod.GET)
	public Boiler getBoilerNew(@RequestParam int id) {
		return boilerService.getBoilerNew(id);
	}


}
