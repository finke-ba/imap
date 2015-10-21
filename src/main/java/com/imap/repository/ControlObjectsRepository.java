package com.imap.repository;

import com.imap.domain.jpa.ControlObjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface ControlObjectsRepository extends JpaRepository<ControlObjects, Integer> {

	List<ControlObjects> findById(Integer id);

	List<ControlObjects> findByBoilerId(Integer id);

	@Query("SELECT co from ControlObjects co WHERE co.boiler.town.id = ?1")
	List<ControlObjects> findByTownId(Integer id);

}
