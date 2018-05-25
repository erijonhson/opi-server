package br.edu.ufcg.dsc.opi.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.dsc.opi.dto.DelegateDTO;
import br.edu.ufcg.dsc.opi.dto.SchoolDTO;
import br.edu.ufcg.dsc.opi.models.OpiCategory;
import br.edu.ufcg.dsc.opi.rest.RestConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchoolRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	public void createDelegate() {
		DelegateDTO delegate = new DelegateDTO("Rohit Gheyi", "rohit@rohit.com");
		ResponseEntity<?> response = this.restTemplate.postForEntity(RestConstants.DELEGATE_URI, delegate,
				DelegateDTO.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertTrue(response.getHeaders().getLocation() != null);
	}

	@Test
	public void createSchoolSuccessTest() {
		createDelegate();
		Set<String> numbers = new HashSet<String>();
		numbers.add("+55 83 5555 5555");
		Set<OpiCategory> categories = new HashSet<>();
		categories.add(OpiCategory.Avançado_Júnior);
		SchoolDTO school = new SchoolDTO("School", "City", 1, numbers, categories);
		ResponseEntity<?> response = this.restTemplate.postForEntity(RestConstants.SCHOOL_URI, school, SchoolDTO.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertTrue(response.getHeaders().getLocation() != null);
	}

	@Test
	public void createSchoolFailTest() {
		SchoolDTO school = new SchoolDTO(null, "City", 1, null, null);
		ResponseEntity<?> response = this.restTemplate.postForEntity(RestConstants.SCHOOL_URI, school, SchoolDTO.class);
		assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
	}

}
