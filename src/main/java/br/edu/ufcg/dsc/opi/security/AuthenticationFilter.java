package br.edu.ufcg.dsc.opi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.JwtException;

/**
 * Filter for Authentication with JWT.
 */
public class AuthenticationFilter extends GenericFilterBean {

    /**
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = SecurityUtils.fillAccessControlHeader((HttpServletResponse) response);
        try {
            HttpServletRequest req = (HttpServletRequest) request;

            /*
             * If it is a OPTIONS request (usually used for check, return OK
             * because there is no header
             */
            if (RequestMethod.OPTIONS.name().equalsIgnoreCase(req.getMethod())) {
                res.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            
            /* Checks the Authorization */
            Authentication auth = TokenAuthenticationService.getAuthentication(req);

            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(request, response);
        } catch (JwtException e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
