package br.edu.ufcg.dsc.opi;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.edu.ufcg.dsc.opi.security.AuthenticationFilter;

/**
 * Security Configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/*/login").permitAll()
				.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
				.antMatchers(HttpMethod.GET, "/configuration/ui/**").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
				.antMatchers(HttpMethod.GET, "/configuration/security/**").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
