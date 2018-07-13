package br.edu.ufcg.dsc.opi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.dsc.opi.admin.AdminDTO;
import br.edu.ufcg.dsc.opi.admin.AdminService;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;

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
		UserModel admin = UserFactory.createAdminObject("admin", "admin@admin.com", "abcde");
		createDefaultAdminIfNotFound(admin);
		alreadySetup = true;
	}

	@Transactional
	private void createDefaultAdminIfNotFound(UserModel admin) {
		AdminDTO savedAdmin = adminService.showByEmail(admin.getEmail());
		if (savedAdmin == null) {
			adminService.create(admin);
		}
	}

}
