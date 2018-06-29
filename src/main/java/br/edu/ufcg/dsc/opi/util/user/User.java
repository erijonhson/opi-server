package br.edu.ufcg.dsc.opi.util.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface to Users of the system.<br>
 * The User on this package is only a abstraction, but don't have Roles, for
 * example.
 * 
 * @author Eri Jonhson
 *
 * @param <UserDTO>
 */
public interface User<UserDTO> extends UserDetails {

	public UserDTO toDTO();

}
