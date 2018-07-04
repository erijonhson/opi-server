package br.edu.ufcg.dsc.opi.admin;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.CryptoUtil;
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
	private AdminRepository adminRepository;

	@Autowired(required = true)
	private UserService userService;

	@Override
	public UserDetails findByLogin(String login) {
		return adminRepository.findByUserEmail(login); 
	}

	@Override
	public AdminModel login(String login, String credentials) {
		AdminModel admin = adminRepository.findByUserEmail(login);
		if (admin != null && CryptoUtil.matches(credentials, admin.getPassword())) {
			admin.setPassword(null);
			return admin;
		}
		return null;
	}

	@Override
	@Transactional
	public AdminModel create(AdminModel admin) {
		admin.setRoles(EnumSet.of(Roles.ADMIN));
		UserModel user = userService.create(admin.getUser());
		admin.setUser(user);
		return adminRepository.save(admin);
	}

	@Override
	public Collection<AdminDTO> indexByEmail(String adminEmail) {
		Collection<AdminDTO> adminsDTO = new HashSet<>();
		Collection<AdminModel> admins = adminRepository.findByUserEmailContaining(adminEmail);
		for (AdminModel admin : admins) {
			admin.setPassword(null);
			adminsDTO.add(admin.toDTO());
		}
		return adminsDTO;
	}

	@Override
	public AdminDTO showByEmail(String email) {
		AdminModel adminModel = adminRepository.findByUserEmail(email);
		if (adminModel == null) {
			return null;
		}
		return adminModel.toDTO();
	}

	@Override
	public boolean changingLocking(String userEmail, boolean status) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ADMIN)) {
			return userService.updateLocked(user.getId(), status);
		}
		return false;
	}

	@Override
	public boolean changingEnabling(String userEmail, boolean status) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ADMIN)) {
			return userService.updateEnable(user.getId(), status);
		}
		return false;
	}

	@Override
	public boolean changingRoles(String userEmail, Set<Roles> roles) {
		UserModel user = userService.findByEmail(userEmail);
		if (user != null && !user.getRoles().contains(Roles.ADMIN)) {
			return userService.updateRoles(user.getId(), roles);
		}
		return false;
	}

}
