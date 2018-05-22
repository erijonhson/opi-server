package com.ufcg.opi.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ufcg.opi.models.Delegate;
import com.ufcg.opi.models.School;
import com.ufcg.opi.rest.RestConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchoolRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void registerSchoolSuccessTest() {
		School school = new School("School", "City", null, new Delegate("Delegate", "delegate@email.com"), null);
		ResponseEntity<School> body = this.restTemplate.postForEntity(
				RestConstants.SCHOOL_URI, school, School.class);
		assertEquals(body.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void registerSchoolFailTest() {
		School school = new School(null, null, null, new Delegate("Delegate", "delegate@email.com"), null);
		ResponseEntity<School> body = this.restTemplate.postForEntity(
				RestConstants.SCHOOL_URI, school, School.class);
		assertEquals(body.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
}
