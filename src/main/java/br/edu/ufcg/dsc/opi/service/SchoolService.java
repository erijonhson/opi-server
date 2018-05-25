package br.edu.ufcg.dsc.opi.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.dto.SchoolDTO;
import br.edu.ufcg.dsc.opi.models.School;
import br.edu.ufcg.dsc.opi.repository.SchoolRepository;

/**
 * Business logic layer to School.
 * 
 * @author Eri Jonhson
 */
@Service(value = "schoolService")
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public School create(School school) {
		return schoolRepository.save(school);
	}

	public Collection<SchoolDTO> index() {
		Collection<SchoolDTO> schoolsDTO = new HashSet<>();
		Collection<School> schools = schoolRepository.findAll();
		for (School school : schools) {
			schoolsDTO.add(new SchoolDTO(
					school.getName(), 
					school.getCity(), 
					school.getDelegate(), 
					school.getSchoolPhoneNumbers(),
					school.getCategories()));
		}
		return schoolsDTO;
	}

}
