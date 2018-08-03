package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.util.Collection;

public interface CompetitorService {

	public CompetitorModel create(CompetitorModel competitor);

	public Collection<CompetitorDTO> indexBySchool(Long schoolId);

	public CompetitorModel update(Long id, CompetitorDTO competitor);

}
