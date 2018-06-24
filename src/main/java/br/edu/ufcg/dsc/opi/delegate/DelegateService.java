package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.security.User;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;
import br.edu.ufcg.dsc.opi.util.UserModel;
import br.edu.ufcg.dsc.opi.util.UserService;

/**
 * Business logic layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Service(value = "delegateService")
public class DelegateService implements br.edu.ufcg.dsc.opi.security.UserService {

	@Autowired(required = true)
	private DelegateRepository delegateRepository;
	
	@Autowired(required = true)
	private UserService userService;

	@Override
	public User login(String login, String credentials) {
		return userService.login(login, credentials);
	}

	@Transactional
	public DelegateModel create(DelegateModel delegate) {
		delegate.setPassword(CryptoUtil.hashPassword(delegate.getPassword()));
		UserModel user = userService.create(delegate.getUser());
		delegate.setUser(user);
		return delegateRepository.save(delegate);
	}

	public Collection<DelegateDTO> indexByEmail(String email) {
		Collection<DelegateDTO> delegatesDTO = new HashSet<>();
		Collection<DelegateModel> delegates = delegateRepository.findByUserEmailContaining(email);
		for (DelegateModel delegate : delegates) {
			delegatesDTO.add((DelegateDTO) delegate.toDTO());
		}
		return delegatesDTO;
	}

	public DelegateDTO showByEmail(String email) {
		DelegateModel delegateModel = delegateRepository.findByUserEmail(email);
		if (delegateModel == null) {
			return null;
		}
		return delegateModel.toDTO();
	}
}
