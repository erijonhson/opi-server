package br.edu.ufcg.dsc.opi.util.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;

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
	public UserModel login(String login, String credentials) {
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

	public UserModel findByEmail(String login) {
		return userRepository.findByEmail(login);
	}

	public boolean updateLocked(Long id, boolean status) {
		UserModel user = userRepository.findById(id).get();
		if (user != null) {
			user.setLocked(status);
			if (userRepository.save(user) != null) {
				return true;
			}
		}
		return false;
	}

	public boolean updateEnable(Long id, boolean status) {
		UserModel user = userRepository.findById(id).get();
		if (user != null) {
			user.setEnabled(status);
			if (userRepository.save(user) != null) {
				return true;
			}
		}
		return false;
	}

	public boolean updateRoles(Long id, Set<Roles> roles) {
		UserModel user = userRepository.findById(id).get();
		if (user != null) {
			user.setRoles(roles);
			if (userRepository.save(user) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public UserDetails findByLogin(String login) {
		return userRepository.findByEmail(login);
	}

}