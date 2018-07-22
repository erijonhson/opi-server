package br.edu.ufcg.dsc.opi.delegate;

import java.util.Collection;

import br.edu.ufcg.dsc.opi.util.user.UserModel;

public interface DelegateService {

	public DelegateDTO login(String login, String credentials);

	public UserModel create(UserModel delegate);

	public Collection<DelegateDTO> indexByEmail(String email);

	public DelegateDTO showByEmail(String email);

	public boolean checkIfDelegate(Long delegateId);

}
