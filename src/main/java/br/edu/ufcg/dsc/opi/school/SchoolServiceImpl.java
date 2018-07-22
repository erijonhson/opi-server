package br.edu.ufcg.dsc.opi.school;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.delegate.DelegateService;

/**
 * Business logic layer to School.
 * 
 * @author Eri Jonhson
 */
@Service(value = "schoolService")
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private DelegateService delegateService;

	public SchoolModel create(SchoolModel school) {
		if (delegateService.checkIfDelegate(school.getDelegate().getId())) {
			return schoolRepository.save(school);
		} else {
			// TODO: internacionalization?
			throw new ValidationException("Colégio precisa de um Delegado válido");
		}
	}

	// @formatter:off
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
	// @formatter:on

}
