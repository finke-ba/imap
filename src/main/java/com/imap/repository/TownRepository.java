package com.imap.repository;

import com.imap.domain.jpa.Boiler;
import com.imap.domain.jpa.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
public interface TownRepository extends JpaRepository<Town, Integer> {

}