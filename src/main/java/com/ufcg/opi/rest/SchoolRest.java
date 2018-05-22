package com.ufcg.opi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.opi.models.School;
import com.ufcg.opi.service.SchoolService;

@RestController
@RequestMapping(value = RestConstants.SCHOOL_URI)
public class SchoolRest {

	@Autowired
	private SchoolService schoolService;

	/**
	 * Endpoint to register a School.
	 * 
	 * @param school spreadsheet to be register
	 * @return status
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	public School registerSchool(@RequestBody School school) {
		return schoolService.register(school);
	}

}
