package br.edu.ufcg.dsc.opi.security;

/**
 * User security interface. All users who can log into the system must implement
 * it.
 * 
 * @author Eri Jonhson
 */
public interface User {

	/**
	 * Single registration information (e.g. username, email, ...).
	 * 
	 * @return {@link String}
	 */
	public String getLogin();

	/**
	 * User default data transfer object.
	 * 
	 * @return {@link UserDTO}
	 */
	public UserDTO toDTO();

}
