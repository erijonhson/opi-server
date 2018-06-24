package br.edu.ufcg.dsc.opi.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.User;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Simple User.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable, User {

	private static final long serialVersionUID = -2449991389290724038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private Roles role;

	public UserModel() {
		this("blank", "blank@blank.com", "blank", null);
	}

	public UserModel(String name, String email, String password, Roles role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.setRole(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String getLogin() {
		return email;
	}

	@Override
	public UserDTO toDTO() {
		return new UserDTO(name, email, password, role);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserModel))
			return false;
		UserModel userModel = (UserModel) obj;
		if (email == null) {
			if (userModel.email != null)
				return false;
		} else if (!email.equals(userModel.email))
			return false;
		if (name == null) {
			if (userModel.name != null)
				return false;
		} else if (!name.equals(userModel.name))
			return false;
		return true;
	}

}
