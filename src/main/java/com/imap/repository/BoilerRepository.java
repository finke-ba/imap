package com.imap.repository;

import com.imap.domain.jpa.Boiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerRepository extends JpaRepository<Boiler, Integer> {

	List<Boiler> findById(Integer id);

}
