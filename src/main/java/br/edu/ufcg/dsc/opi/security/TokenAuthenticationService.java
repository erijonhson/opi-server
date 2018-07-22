package br.edu.ufcg.dsc.opi.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.edu.ufcg.dsc.opi.util.JSonUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Service for Token Authentication.
 */
public class TokenAuthenticationService {

	private static String SECRET_KEY = "0p153rv3r53cr3t"; // TODO: put in .env
	private static final long EXPIRATION_TOKEN = 432_000_000L; // 5 days // TODO: put in .env
	private static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER = "Authorization";

	/**
	 * Gets the authentication.
	 *
	 * @param request
	 *            Request.
	 * @return Authentication.
	 */
	static Authentication getAuthentication(final HttpServletRequest request) {

		String token = request.getHeader(HEADER);
		if (token != null) {
			// @formatter:off
			String user = Jwts
					.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
					.getBody()
					.getSubject();
			Payload userDetails = JSonUtil.fromJSon(user, Payload.class);
			if (!userDetails.isAccountNonLocked()) {
				throw new LockedAccountRuntimeException();
			}
			if (!userDetails.isEnabled()) {
				throw new DisabledAccountRuntimeException();
			}
			if (userDetails.isAccountNonLocked() && userDetails.isEnabled()) {
				return new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(),
						null,
						userDetails.getAuthorities());
			}
			// @formatter:on
		}
		return null;
	}

	/**
	 * Generate Token.
	 * 
	 * @param payload
	 * @return token
	 */
	public static final String generateToken(final Payload payload) {
		// @formatter:off
		return Jwts
				.builder()
				.setSubject(JSonUtil.toJSon(payload))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		// @formatter:on
	}

}
