package br.edu.ufcg.dsc.opi.admin;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.dsc.opi.security.Payload;
import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.TokenAuthenticationService;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import br.edu.ufcg.dsc.opi.util.user.UserService;

/**
 * Business logic layer to Admin.
 * 
 * @author Eri Jonhson
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired(required = true)
	private UserService userService;

	@Override
	public AdminDTO login(String login, String credentials) {
		UserModel admin = userService.findByEmail(login);
		if (admin != null && CryptoUtil.matches(credentials, admin.getPassword())) {
			admin.setPassword(null);
			Payload payload = UserFactory.createPayload(admin);
			String token = TokenAuthenticationService.generateToken(payload);
			AdminDTO adminDTO = AdminDTO.toDTO(admin);
			adminDTO.setToken(token);
			return adminDTO;
		}
		return null;
	}

	@Override
	@Transactional
	public UserModel create(UserModel admin) {
		admin.setRoles(EnumSet.of(Roles.ROLE_ADMIN));
		admin.setLocked(false);
		admin.setEnabled(true);
		return userService.create(admin);
	}

	@Override
	public Collection<AdminDTO> indexByEmail(String adminEmail) {
		Collection<UserModel> admins = userService.findByEmailContaining(adminEmail);
		return AdminDTO.toDTO(admins);
	}

	@Override
	public AdminDTO showByEmail(String email) {
		UserModel adminModel = userService.findByEmail(email);
		if (adminModel == null) {
			return null;
		}
		return AdminDTO.toDTO(adminModel);
	}

	@Override
	public boolean changingLocking(String userEmail, boolean status) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ROLE_ADMIN)) {
			return userService.updateLocked(user.getId(), status);
		}
		return false;
	}

	@Override
	public boolean changingEnabling(String userEmail, boolean status) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ROLE_ADMIN)) {
			return userService.updateEnable(user.getId(), status);
		}
		return false;
	}

	@Override
	public boolean changingRoles(String userEmail, Set<Roles> roles) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ROLE_ADMIN)) {
			return userService.updateRoles(user.getId(), roles);
		}
		return false;
	}

}
