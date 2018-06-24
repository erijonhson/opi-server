package br.edu.ufcg.dsc.opi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.security.User;

/**
 * Business logic layer to Simple User.
 * 
 * @author Eri Jonhson
 */
@Service(value = "userService")
public class UserService implements br.edu.ufcg.dsc.opi.security.UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User login(String login, String credentials) {
		UserModel user = userRepository.findByEmail(login);
		if (user != null && CryptoUtil.matches(credentials, user.getPassword())) {
			user.setPassword(null);
			return user;
		}
		return null;
	}

	public UserModel create(UserModel user) {
		return userRepository.save(user);
	}

}
