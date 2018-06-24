package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "delegateRepository")
interface DelegateRepository extends JpaRepository<DelegateModel, Long> {

	public Collection<DelegateModel> findByUserEmailContaining(String email);

	public DelegateModel findByUserEmail(String email);

}
