package br.edu.ufcg.dsc.opi.security;

import javax.servlet.http.HttpServletResponse;

/**
 * Utils for Security.
 */
public final class SecurityUtils {

    /**
     * Fills the Header with the Access Control.
     *
     * @param response
     *            Response.
     * @return Response.
     */
    public static HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
        response.addHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, refresh-token, user");
        response.addHeader("Access-Control-Expose-Headers", "authorization, content-type, refresh-token, user");

        return response;
    }
}
