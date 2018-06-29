package br.edu.ufcg.dsc.opi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public UserDetails findByLogin(String login);
	public UserDetails login(String login, String credentials);

}
