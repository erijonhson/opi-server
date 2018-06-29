package br.edu.ufcg.dsc.opi.delegate;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.UserDTO;
import br.edu.ufcg.dsc.opi.util.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Delegate")
public class DelegateDTO implements DTO<DelegateModel>, UserDTO {

	@ApiModelProperty(example = "Rohit Gheyi")
	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	private String name;

	@ApiModelProperty(example = "rohit@rohit.com")
	@NotEmpty
	@Email
	private String email;

	@ApiModelProperty(example = "abcde")
	@NotEmpty
	private String password;

	@ApiModelProperty(example = "DELEGATE")
	private Set<Roles> roles;

	@ApiModelProperty(name = "token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJleHAiOjE1MzAyODcyNzV9.orNXJJBUYC0Orcm-zMJULaPlcnwB9Iw7UwM532XVEYhclH624MzI32zKDIRTKHEYwtOPg_OJMQyin4itxs9MjA")
	private String token;

	public DelegateDTO() {
		this("blank", "blank");
	}

	public DelegateDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public DelegateDTO(String name, String email, String password, Set<Roles> roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public DelegateModel toModel() {
		return new DelegateModel(getName(), getEmail(), getPassword(), getRoles());
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

	public Set<Roles> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token= token;
	}

}
