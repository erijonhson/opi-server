package br.edu.ufcg.dsc.opi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.dsc.opi.models.School;

/**
 * Persistence layer to School.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "schoolRepository")
public interface SchoolRepository extends JpaRepository<School, Long> {

}
