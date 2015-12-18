package com.imap.controllers;

import com.imap.domain.jpa.Boiler;
import com.imap.domain.jpa.ControlObject;
import com.imap.services.BoilerRegionService;
import com.imap.services.BoilerService;
import com.imap.services.BoilerTownService;
import com.imap.services.temp.BoilerRegionServiceNew;
import com.imap.services.temp.BoilerServiceNew;
import com.imap.services.temp.BoilerTownServiceNew;
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
	private BoilerService boilerServiceOld;

	@Autowired
	private BoilerTownService boilerTownServiceOld;

	@Autowired
	private BoilerRegionService boilerRegionServiceOld;

	@Autowired
	private BoilerServiceNew boilerService;

	@Autowired
	private BoilerTownServiceNew boilerTownService;

	@Autowired
	private BoilerRegionServiceNew boilerRegionService;

//	@RequestMapping(value = "/get/boilers/region", method = RequestMethod.GET)
//	public List<BoilerRegion> getBoilersForRegion() {
//		return boilerServiceOld.getBoilersForRegion();
//	}
//
//	@RequestMapping(value = "/get/boilers/town", method = RequestMethod.GET)
//	public List<BoilerTown> getBoilersForTown(@RequestParam int id) {
//		return boilerServiceOld.getBoilersForTown(id);
//	}
//
//	@RequestMapping(value = "/get/boiler", method = RequestMethod.GET)
//	public List<com.imap.domain.jdbc.Boiler> getBoiler(@RequestParam int id) {
//		return boilerServiceOld.getBoiler(id);
//	}
//
	@RequestMapping(value = "/get/boilerCO/new", method = RequestMethod.GET)
	public List<ControlObject> getBoilerCONew(@RequestParam int id) {
		return boilerServiceOld.getBoilerCONew(id);
	}

	@RequestMapping(value = "/get/boilers/town/new", method = RequestMethod.GET)
	public List<ControlObject> getBoilersForTownNew(@RequestParam int id) {
		return boilerServiceOld.getBoilersForTownNew(id);
	}
//
//	@RequestMapping(value = "/get/boilers/region/new", method = RequestMethod.GET)
//	public List<ControlObject> getBoilersForRegionNew() {
//		return boilerServiceOld.getBoilersForRegionNew();
//	}

	@RequestMapping(value = "/get/town", method = RequestMethod.GET)
	public BoilerTownUIVO getTownNameNew(@RequestParam int id) {
		return boilerTownService.getTown(id);
	}

	@RequestMapping(value = "/get/boiler", method = RequestMethod.GET)
	public BoilerTownUIVO getBoiler(@RequestParam int id) {
		return boilerService.getBoiler(id);
	}

	@RequestMapping(value = "/get/boiler/check/old", method = RequestMethod.GET)
	public List<List<BoilerUIVO>> getBoilerCheckOld(@RequestParam int id) {
		return boilerServiceOld.getBoilerCheck(id);
	}

	@RequestMapping(value = "/get/boilers/town/check/old", method = RequestMethod.GET)
	public List<List<BoilerTownUIVO>> getBoilersForTownCheckOld(@RequestParam int id) {
		return boilerTownServiceOld.getBoilersForTownCheck(id);
	}

	@RequestMapping(value = "/get/boilers/region/check/old", method = RequestMethod.GET)
	public List<BoilerRegionUIVO> getBoilersForRegionCheckOld() {
		return boilerRegionServiceOld.getBoilersForRegionCheck();
	}

	@RequestMapping(value = "/get/boiler/check", method = RequestMethod.GET)
	public List<BoilerUIVO> getBoilerCheck(@RequestParam int id) {
		return boilerService.getBoilerChecked(id);
	}

	@RequestMapping(value = "/get/boilers/town/check", method = RequestMethod.GET)
	public List<BoilerTownUIVO> getBoilersForTownCheck(@RequestParam int id) {
		return boilerTownService.getBoilersInTownChecked(id);
	}

	@RequestMapping(value = "/get/boilers/region/check", method = RequestMethod.GET)
	public List<BoilerRegionUIVO> getBoilersForRegionCheck() {
		return boilerRegionService.getBoilersForRegionCheck();
	}


}
