package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.school.student.StudentModel;
import br.edu.ufcg.dsc.opi.school.student.StudentService;

@Service(value = "competitorService")
public class CompetitorServiceImpl implements CompetitorService {

	@Autowired
	CompetitorRepository competitorRepository;

	@Autowired
	StudentService studentService;

	@Override
	public CompetitorModel create(CompetitorModel competitor) {
		StudentModel savedCompetitor = studentService.findById(competitor.getId()).get();
		competitor.setStudent(savedCompetitor);
		return competitorRepository.save(competitor);
	}

	@Override
	public Collection<CompetitorDTO> indexBySchool(Long schoolId) {
		Collection<CompetitorModel> competitors = competitorRepository.findAllByStudentSchoolId(schoolId);
		return CompetitorDTO.toDTO(competitors);
	}

	@Override
	public CompetitorModel update(Long id, CompetitorDTO competitorDTO) {
		CompetitorModel competitor = competitorDTO.toModel();
		competitor.setId(id);
		return null;
	}

}
