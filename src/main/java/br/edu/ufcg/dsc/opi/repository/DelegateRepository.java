package br.edu.ufcg.dsc.opi.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.dsc.opi.models.Delegate;

/**
 * Persistence layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "delegateRepository")
public interface DelegateRepository extends JpaRepository<Delegate, Long> {

	public Collection<Delegate> findByEmail(String email);

}
