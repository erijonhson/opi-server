package br.edu.ufcg.dsc.opi.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to School.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "SchoolRepository")
interface SchoolRepository extends JpaRepository<SchoolModel, Long> {

}
