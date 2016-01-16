package com.imap.services.impl;

import com.imap.exceptions.NoSuchItemException;
import com.imap.services.ValidationCacheService;
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

	/** Интерфейс доступа к кэшированным данным по региону. */
	@Autowired
	private ValidationCacheService validationCacheService;

	@Override
	public List<RegionUIVO> getBoilersForRegionChecked() throws NoSuchItemException {
		return validationCacheService.getRegionChecked();
	}

}
