package br.edu.ufcg.dsc.opi.olympiad.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to Competition.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "competitionRepository")
interface CompetitionRepository extends JpaRepository<CompetitionModel, Long> {

}
