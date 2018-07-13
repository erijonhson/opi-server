package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufcg.dsc.opi.security.UserService;
import br.edu.ufcg.dsc.opi.util.user.UserModel;

public interface DelegateService extends UserService {

	public UserModel create(UserModel delegate);

	public Collection<DelegateDTO> indexByEmail(String email);

	public DelegateDTO showByEmail(String email);

	public UserDetails findByLogin(String login);

}
