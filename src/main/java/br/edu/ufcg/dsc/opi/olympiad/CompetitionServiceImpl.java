package br.edu.ufcg.dsc.opi.olympiad;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.util.NotFoundRuntimeException;

/**
 * Business logic layer to Competition.
 * 
 * @author Eri Jonhson
 */
@Service(value = "competitionService")
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired(required = true)
	private CompetitionRepository competitionRepository;

	@Override
	public CompetitionModel create(CompetitionModel competition) {
		return competitionRepository.save(competition);
	}

	@Override
	public Collection<CompetitionDTO> index() {
		Collection<CompetitionModel> competitions = competitionRepository.findAll();
		return CompetitionDTO.toDTO(competitions);
	}

	@Override
	public CompetitionModel update(Long id, CompetitionDTO competitionDTO) {
		if (!competitionRepository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		CompetitionModel competition = competitionDTO.toModel();
		competition.setId(id);
		return competitionRepository.save(competition);
	}

}
