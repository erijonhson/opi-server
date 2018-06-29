package br.edu.ufcg.dsc.opi.school;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic layer to School.
 * 
 * @author Eri Jonhson
 */
@Service(value = "schoolService")
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public SchoolModel create(SchoolModel school) {
		return schoolRepository.save(school);
	}

	public Collection<SchoolDTO> index() {
		Collection<SchoolDTO> schoolsDTO = new HashSet<>();
		Collection<SchoolModel> schools = schoolRepository.findAll();
		for (SchoolModel school : schools) {
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
