package br.edu.ufcg.dsc.opi.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.edu.ufcg.dsc.opi.util.JSonUtil;

/**
 * Filter for Login.
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	/**
	 * Constructor.
	 *
	 * @param url
	 *            URL.
	 */
	public LoginFilter(String url) {
		super(new AntPathRequestMatcher(url));
		this.setAuthenticationManager(new JWTAuthenticationManager());
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		response = SecurityUtils.fillAccessControlHeader(response);
		AccountCredentials creds = JSonUtil.fromJSon(request.getReader(), AccountCredentials.class);
		return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
				creds.getPassword(), Collections.emptyList()));
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
	 *      org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		User user = (User) auth.getPrincipal();
		UserDTO dto = user.toDTO();
		TokenAuthenticationService.addAuthentication(dto, user.getLogin());
		response.getWriter().write(JSonUtil.toJSon(dto));
	}
}
