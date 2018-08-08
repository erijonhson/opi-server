package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to Competitor.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "competitorRepository")
interface CompetitorRepository extends JpaRepository<CompetitorModel, Long> {

	Collection<CompetitorModel> findAllByStudentSchoolId(Long schoolId);

}
