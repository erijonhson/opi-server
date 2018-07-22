package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.security.Payload;
import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.TokenAuthenticationService;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
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
	private UserService userService;

	@Override
	public DelegateDTO login(final String login, final String credentials) {
		UserModel delegate = userService.findByEmail(login);
		if (delegate != null && CryptoUtil.matches(credentials, delegate.getPassword())) {
			delegate.setPassword(null);
			Payload payload = UserFactory.createPayload(delegate);
			final String token = TokenAuthenticationService.generateToken(payload);
			DelegateDTO delegateDTO = DelegateDTO.toDTO(delegate);
			delegateDTO.setToken(token);
			return delegateDTO;
		}
		return null;
	}

	public UserModel create(UserModel delegate) {
		delegate.setRoles(EnumSet.of(Roles.ROLE_DELEGATE));
		return userService.create(delegate);
	}

	public Collection<DelegateDTO> indexByEmail(final String email) {
		Collection<UserModel> delegates = userService.findByEmailContaining(email);
		return DelegateDTO.toDTO(delegates);
	}

	public DelegateDTO showByEmail(final String email) {
		UserModel delegate = userService.findByEmail(email);
		if (delegate == null) {
			return null;
		}
		return DelegateDTO.toDTO(delegate);
	}

	@Override
	public boolean checkIfDelegate(final Long delegateId) {
		final UserModel delegate = userService.findById(delegateId);
		if (delegate.getRoles().contains(Roles.ROLE_DELEGATE))
			return true;
		else
			return false;
	}

}
