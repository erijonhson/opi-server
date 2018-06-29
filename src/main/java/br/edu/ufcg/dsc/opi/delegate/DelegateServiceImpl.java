package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import br.edu.ufcg.dsc.opi.util.user.UserService;

/**
 * Business logic layer to Delegate.
 * 
 * @author Eri Jonhson
 */
@Service(value = "delegateService")
public class DelegateServiceImpl implements DelegateService {

	@Autowired(required = true)
	private DelegateRepository delegateRepository;

	@Autowired(required = true)
	private UserService userService;

	@Override
	public DelegateModel login(String login, String credentials) {
		DelegateModel delegate = delegateRepository.findByUserEmail(login);
		if (delegate != null && CryptoUtil.matches(credentials, delegate.getPassword())) {
			delegate.setPassword(null);
			return delegate;
		}
		return null;
	}

	@Transactional
	public DelegateModel create(DelegateModel delegate) {
		delegate.setRoles(EnumSet.of(Roles.DELEGATE));
		UserModel user = userService.create(delegate.getUser());
		delegate.setUser(user);
		return delegateRepository.save(delegate);
	}

	public Collection<DelegateDTO> indexByEmail(String email) {
		Collection<DelegateDTO> delegatesDTO = new HashSet<>();
		Collection<DelegateModel> delegates = delegateRepository.findByUserEmailContaining(email);
		for (DelegateModel delegate : delegates) {
			delegate.setPassword(null);
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

	@Override
	public UserDetails findByLogin(String login) {
		return delegateRepository.findByUserEmail(login);
	}
}
