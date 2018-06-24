package br.edu.ufcg.dsc.opi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.edu.ufcg.dsc.opi.ApplicationContextHolder;

/**
 * Authentication Manager.
 */
public class JWTAuthenticationManager implements AuthenticationManager {

	private UserService userService;

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String password = (String) auth.getCredentials();
		User loggedUser = getUserService().login(auth.getName(), password);
		if (loggedUser != null) {
			return new UsernamePasswordAuthenticationToken(loggedUser, password);
		}
		throw new BadCredentialsException("Usuário e/ou senha inválidos");
	}

	protected UserService getUserService() {
		if (userService == null) {
			userService = ApplicationContextHolder.getBean(br.edu.ufcg.dsc.opi.util.UserService.class);
		}
		return userService;
	}

}
