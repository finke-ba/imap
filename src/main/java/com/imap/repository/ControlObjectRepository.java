package com.imap.repository;

import com.imap.domain.jpa.ControlObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface ControlObjectRepository extends JpaRepository<ControlObject, Integer> {

	List<ControlObject> findById(Integer id);

	List<ControlObject> findByBoilerId(Integer id);

	@Query("SELECT co FROM ControlObject co WHERE co.boiler.town.id = ?1")
	List<ControlObject> findByTownId(Integer id);

}
