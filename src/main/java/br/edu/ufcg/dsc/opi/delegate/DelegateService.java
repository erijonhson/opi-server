package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufcg.dsc.opi.security.UserService;

public interface DelegateService extends UserService {

	public DelegateModel create(DelegateModel delegate);

	public Collection<DelegateDTO> indexByEmail(String email);

	public DelegateDTO showByEmail(String email);

	public UserDetails findByLogin(String login);

}
