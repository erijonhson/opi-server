package br.edu.ufcg.dsc.opi.util.user;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufcg.dsc.opi.security.Roles;

/**
 * Interface to Simple User.
 * 
 * @author Eri Jonhson
 */
public interface User extends UserDetails {

	public String getName();

	public void setName(String name);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);

	public Set<Roles> getRoles();

	public void setRoles(Set<Roles> roles);

	public void setLocked(boolean locked);

	public void setEnabled(boolean enabled);

}
