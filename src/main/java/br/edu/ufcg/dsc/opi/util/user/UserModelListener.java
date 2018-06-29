package br.edu.ufcg.dsc.opi.util.user;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.edu.ufcg.dsc.opi.util.CryptoUtil;

/**
 * Listener to Simple User.
 * 
 * @author Eri Jonhson
 */
public class UserModelListener {

	@PrePersist
	@PreUpdate
	public void methodExecuteBeforeSave(final UserModel user) {
		user.setPassword(CryptoUtil.hashPassword(user.getPassword()));
	}

}
