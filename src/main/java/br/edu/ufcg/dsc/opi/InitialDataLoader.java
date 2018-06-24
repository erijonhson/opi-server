package br.edu.ufcg.dsc.opi;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.edu.ufcg.dsc.opi.delegate.DelegateDTO;
import br.edu.ufcg.dsc.opi.delegate.DelegateModel;
import br.edu.ufcg.dsc.opi.delegate.DelegateService;
import br.edu.ufcg.dsc.opi.security.Roles;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired(required = true)
	DelegateService delegateService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		// TODO: Admin
		createDefaultAdminIfNotFound(new DelegateModel("admin", "admin@admin.com", "abcde", Roles.ADMIN));

		alreadySetup = true;
	}

	@Transactional
	private DelegateModel createDefaultAdminIfNotFound(DelegateModel delegate) {
		DelegateDTO savedDelegate = delegateService.showByEmail(delegate.getEmail());
		if (savedDelegate == null) {
			return delegateService.create(delegate);
		}
		return delegate;
	}

}