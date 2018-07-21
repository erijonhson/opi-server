package br.edu.ufcg.dsc.opi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * JWT claims.
 * 
 * @author Eri Jonhson
 */
public class Payload implements UserDetails {

	private static final long serialVersionUID = -4406190873913296752L;

	private String email;
	private Roles[] roles;
	private boolean enabled;
	private boolean locked;

	public Payload(String email, Roles[] roles, boolean enabled, boolean locked) {
		this.email = email;
		this.roles = roles;
		this.enabled = enabled;
		this.locked = locked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] roles = new String[this.roles.length];
		int index = 0;
		for (Roles role : this.roles) {
			roles[index++] = role.toString();
		}
		return AuthorityUtils.createAuthorityList(roles);
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("email: " + email);
		sb.append(" roles: ");
		for (Roles r : roles)
			sb.append(r.toString() + ", ");
		sb.append("enable: " + enabled);
		sb.append(" locked: " + locked);
		return sb.toString();
	}

}
