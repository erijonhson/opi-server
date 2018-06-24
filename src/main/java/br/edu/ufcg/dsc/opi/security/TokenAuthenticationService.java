package br.edu.ufcg.dsc.opi.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Service for Token Authentication.
 */
public class TokenAuthenticationService {

	private static final long EXPIRATION_TOKEN = 432_000_000L; // 5 days
	private static final long EXPIRATION_REFRESH = 2_592_000_000L; // 30 days
	private static final String SECRET_KEY = "0p153rv3r53cr3t"; // TODO: put in a .env
	private static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER = "Authorization";
	protected static final String HEADER_REFRESH = "Refresh-Token";

	/**
	 * Adds the authentication in the response.
	 *
	 * @param response
	 *            Response.
	 * @param username
	 *            Username.
	 */
	static void addAuthentication(UserDTO dto, String username) {

		String jwt = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

		String refresh = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

		dto.setToken(jwt);
		dto.setRefreshToken(refresh);
	}

	/**
	 * Gets the authentication.
	 *
	 * @param request
	 *            Request.
	 * @return Authentication.
	 */
	static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
					.getBody().getSubject();
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}

	/**
	 * Generate Token.
	 *
	 * @param username
	 *            Username.
	 * @return Token.
	 */
	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	/**
	 * Gets the refresh authentication.
	 *
	 * @param request
	 *            Request.
	 * @return Refresh Authentication.
	 */
	static Authentication getRefreshAuthetication(HttpServletRequest request) {
		String refreshToken = request.getHeader(HEADER_REFRESH);
		if (refreshToken != null) {
			String user = Jwts.parser().setSigningKey(SECRET_KEY)
					.parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody().getSubject();
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}
}
