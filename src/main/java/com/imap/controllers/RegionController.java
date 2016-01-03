package com.imap.controllers;

import com.imap.services.RegionService;
import com.imap.uivo.RegionUIVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/imap/region")
public class RegionController {

	@Autowired
	private RegionService boilerRegionService;

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public List<RegionUIVO> getBoilersForRegionCheck() {
		return boilerRegionService.getBoilersForRegionChecked();
	}

}
