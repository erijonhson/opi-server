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
	private static final long EXPIRATION_TOKEN = 432_000_000L; // 5 days
	private static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER = "Authorization";
	protected static final String HEADER_REFRESH = "Refresh-Token";

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
			String user = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
					.getBody().getSubject();
			Payload userDetails = JSonUtil.fromJSon(user, Payload.class);
			if (userDetails != null && userDetails.isAccountNonLocked() && userDetails.isEnabled()) {
				// User(userDetails.getUsername(), null, userDetails.isEnabled(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), userDetails.isAccountNonLocked(), userDetails.getAuthorities());
				return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
			} else {
				return null;
			}
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
		return Jwts.builder().setSubject(JSonUtil.toJSon(payload))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

}
