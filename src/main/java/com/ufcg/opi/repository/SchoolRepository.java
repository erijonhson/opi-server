package com.ufcg.opi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.opi.models.School;

/**
 * Persistence layer to School.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "schoolRepository")
public interface SchoolRepository extends JpaRepository<School, Long> {

}
