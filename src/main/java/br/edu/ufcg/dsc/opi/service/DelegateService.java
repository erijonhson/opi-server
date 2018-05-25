package br.edu.ufcg.dsc.opi.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.dto.DelegateDTO;
import br.edu.ufcg.dsc.opi.models.Delegate;
import br.edu.ufcg.dsc.opi.repository.DelegateRepository;

/**
 * Business logic layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Service(value = "delegateService")
public class DelegateService {

	@Autowired
	private DelegateRepository delegateRepository;

	public Delegate create(Delegate delegate) {
		return delegateRepository.save(delegate);
	}

	public Collection<DelegateDTO> indexByEmail(String email) {
		Collection<DelegateDTO> delegatesDTO = new HashSet<>();
		Collection<Delegate> delegates = delegateRepository.findByEmail(email);
		for (Delegate delegate : delegates) {
			delegatesDTO.add(new DelegateDTO(
					delegate.getName(), 
					delegate.getEmail()));
		}
		return delegatesDTO;
	}

}
