package com.imap.services.impl;

import com.imap.services.BoilerMapService;
import com.imap.services.RegionService;
import com.imap.uivo.RegionUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для проверки и получения информации о всех котельных во всех городах в регионе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@Service
public class RegionServiceImpl implements RegionService {

	/** Интерфейс доступа к данным по котельным. */
	@Autowired
	private BoilerMapService boilerMapService;

	@Override
	public List<RegionUIVO> getBoilersForRegionChecked() {
		return boilerMapService.getRegionChecked();
	}

}
