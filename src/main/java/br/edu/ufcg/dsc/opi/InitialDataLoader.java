package br.edu.ufcg.dsc.opi;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.dsc.opi.admin.AdminDTO;
import br.edu.ufcg.dsc.opi.admin.AdminModel;
import br.edu.ufcg.dsc.opi.admin.AdminService;
import br.edu.ufcg.dsc.opi.security.Roles;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	AdminService adminService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		AdminModel admin = new AdminModel("admin", "admin@admin.com", "abcde", EnumSet.of(Roles.ADMIN));
		createDefaultAdminIfNotFound(admin);
		alreadySetup = true;
	}

	@Transactional
	private void createDefaultAdminIfNotFound(AdminModel admin) {
		AdminDTO savedAdmin = adminService.showByEmail(admin.getEmail());
		if (savedAdmin == null) {
			adminService.create(admin);
		}
	}

}
