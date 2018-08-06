package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.school.student.StudentService;
import br.edu.ufcg.dsc.opi.util.NotFoundRuntimeException;

@Service(value = "competitorService")
public class CompetitorServiceImpl implements CompetitorService {

	@Autowired
	CompetitorRepository competitorRepository;

	@Autowired
	StudentService studentService;

	@Override
	public CompetitorModel create(CompetitorModel competitor) {
		return competitorRepository.save(competitor);
	}

	@Override
	public Collection<CompetitorDTO> indexBySchool(Long schoolId) {
		Collection<CompetitorModel> competitors = competitorRepository.findAllByStudentSchoolId(schoolId);
		return CompetitorDTO.toDTO(competitors);
	}

	@Override
	public CompetitorModel update(Long id, CompetitorDTO competitorDTO) {
		CompetitorModel competitor = competitorRepository.getOne(id);
		if (competitor == null) {
			throw new NotFoundRuntimeException();
		}
		competitor.setMarkLevelOne(competitorDTO.getMarkLevelOne());
		competitor.setMarkLevelTwo(competitorDTO.getMarkLevelTwo());
		return competitorRepository.save(competitor);
	}

}
