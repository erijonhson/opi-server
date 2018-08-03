package br.edu.ufcg.dsc.opi.olympiad.competition;

import java.util.Collection;

public interface CompetitionService {

	public CompetitionModel create(CompetitionModel competition);

	public Collection<CompetitionDTO> index();

	public CompetitionModel update(Long id, CompetitionDTO competition);

}
