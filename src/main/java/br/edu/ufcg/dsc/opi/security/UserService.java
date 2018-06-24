package br.edu.ufcg.dsc.opi.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public interface UserService {

	public User login(String login, String credentials);

}
