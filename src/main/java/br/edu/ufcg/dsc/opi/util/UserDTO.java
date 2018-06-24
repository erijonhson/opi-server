package br.edu.ufcg.dsc.opi.util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ufcg.dsc.opi.security.Roles;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class UserDTO implements DTO<UserModel>, br.edu.ufcg.dsc.opi.security.UserDTO {

	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	private String name;

	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;

	@NotEmpty
	private Roles role;

	@SuppressWarnings("unused")
	private String jwt;

	@SuppressWarnings("unused")
	private String refresh;

	public UserDTO() {
		this("blank", "blank", "blank", null);
	}

	public UserDTO(String name, String email, String password, Roles role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public UserModel toModel() {
		return new UserModel(name, email, password, role);
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

	@Override
	public void setToken(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public void setRefreshToken(String refresh) {
		this.refresh = refresh;
	}

}
