package com.imap.controllers;

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
 * Контроллер для интерактивной карты телеметрии.
 *
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

	@RequestMapping(value = "/get/town", method = RequestMethod.GET)
	public BoilerTownUIVO getTownNameNew(@RequestParam int id) {
		return boilerTownService.getTown(id);
	}

	@RequestMapping(value = "/get/boiler", method = RequestMethod.GET)
	public BoilerTownUIVO getBoiler(@RequestParam int id) {
		return boilerService.getBoiler(id);
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
