package br.edu.ufcg.dsc.opi;

import java.util.EnumSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.edu.ufcg.dsc.opi.delegate.DelegateDTO;
import br.edu.ufcg.dsc.opi.delegate.DelegateModel;
import br.edu.ufcg.dsc.opi.delegate.DelegateServiceImpl;
import br.edu.ufcg.dsc.opi.security.Roles;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	DelegateServiceImpl delegateService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		createDefaultDelegateIfNotFound(new DelegateModel("delegate", "delegate@delegate.com", "abcde", EnumSet.of(Roles.DELEGATE)));

		alreadySetup = true;
	}

	@Transactional
	private DelegateModel createDefaultDelegateIfNotFound(DelegateModel delegate) {
		DelegateDTO savedDelegate = delegateService.showByEmail(delegate.getEmail());
		if (savedDelegate == null) {
			return delegateService.create(delegate);
		}
		return delegate;
	}

}