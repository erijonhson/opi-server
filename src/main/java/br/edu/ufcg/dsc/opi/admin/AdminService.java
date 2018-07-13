package br.edu.ufcg.dsc.opi.admin;

import java.util.Collection;
import java.util.Set;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.UserService;
import br.edu.ufcg.dsc.opi.util.user.UserModel;

public interface AdminService extends UserService {

	public UserModel create(UserModel admin);

	public Collection<AdminDTO> indexByEmail(String adminEmail);

	public AdminDTO showByEmail(String adminEmail);

	public boolean changingLocking(String userEmail, boolean status);

	public boolean changingEnabling(String userEmail, boolean status);

	public boolean changingRoles(String userEmail, Set<Roles> roles);

}
