package br.edu.ufcg.dsc.opi.delegate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.User;
import br.edu.ufcg.dsc.opi.util.UserModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Delegate responsible for one or more schools.
 * 
 * @author Eri Jonhson
 */
@Entity
@Table(name = "tb_delegate")
@ApiIgnore
public class DelegateModel implements Serializable, User {

	private static final long serialVersionUID = -4762740963019321048L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne
    @JoinColumn(name = "user_id")
	private UserModel user;

	public DelegateModel() {
		this("blank", "blank@blank.com", "blank", Roles.DELEGATE);
	}

	public DelegateModel(String name, String email, String password, Roles role) {
		user = new UserModel(name, email, password, role);
	}

	public DelegateModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		user.setName(name);
	}

	public String getEmail() {
		return user.getEmail();
	}

	public void setEmail(String email) {
		user.setEmail(email);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public Roles getRole() {
		return user.getRole();
	}

	public void setRole(Roles role) {
		user.setRole(role);
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public String getLogin() {
		return user.getLogin();
	}

	@Override
	public String[] getRoles() {
		return user.getRoles();
	}

	@Override
	public DelegateDTO toDTO() {
		return new DelegateDTO(user.getName(), user.getEmail(), user.getPassword(), user.getRole());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DelegateModel))
			return false;
		DelegateModel delegate = (DelegateModel) obj;
		if (getEmail() == null) {
			if (getEmail() != null)
				return false;
		} else if (!getEmail().equals(delegate.getEmail()))
			return false;
		if (getName() == null) {
			if (delegate.getName() != null)
				return false;
		} else if (!getName().equals(delegate.getName()))
			return false;
		return true;
	}

}
