package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Service(value = "delegateService")
public class DelegateService {

	@Autowired
	private DelegateRepository delegateRepository;

	public DelegateModel create(DelegateModel delegate) {
		return delegateRepository.save(delegate);
	}

	public Collection<DelegateDTO> indexByEmail(String email) {
		Collection<DelegateDTO> delegatesDTO = new HashSet<>();
		Collection<DelegateModel> delegates = delegateRepository.findByEmailContaining(email);
		for (DelegateModel delegate : delegates) {
			delegatesDTO.add(new DelegateDTO(
					delegate.getName(), 
					delegate.getEmail()));
		}
		return delegatesDTO;
	}

}
