package com.imap.repository;

import com.imap.domain.Boiler;
import com.imap.domain.BoilerNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface BoilerRepository extends JpaRepository<BoilerNew, Integer> {

	List<BoilerNew> findById(Integer id);

}
