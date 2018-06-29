package br.edu.ufcg.dsc.opi.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import br.edu.ufcg.dsc.opi.ApplicationContextHolder;

/**
 * Utils for Security.
 */
public class SecurityUtils {

	private static UserService userService;

	/**
	 * Fills the Header with the Access Control.
	 *
	 * @param response
	 *            Response.
	 * @return Response.
	 */
	public static HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

		response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, DELETE, PUT, PATCH");
		response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with, authorization, content-type, refresh-token, user");
		response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "authorization, content-type, refresh-token, user");

		return response;
	}

	/**
	 * Get Headers with the Access Control.
	 * 
	 * @return HttpHeaders
	 */
	public static HttpHeaders fillAccessControlHeader() {

		HttpHeaders headers = new HttpHeaders();

		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, DELETE, PUT, PATCH");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with, authorization, content-type, refresh-token, user");
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "authorization, content-type, refresh-token, user");
		return headers;
	}

	protected static UserService getUserService() {
		if (userService == null) {
			userService = ApplicationContextHolder.getBean("userService", UserService.class);
		}
		return userService;
	}
}
