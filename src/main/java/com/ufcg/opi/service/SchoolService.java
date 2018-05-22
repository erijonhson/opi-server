package com.ufcg.opi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.opi.models.School;
import com.ufcg.opi.repository.SchoolRepository;

/**
 * Business logic layer to School.
 * 
 * @author Eri Jonhson
 */
@Service(value = "schoolService")
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public School register(School school) {
		return schoolRepository.saveAndFlush(school);
	}

}
