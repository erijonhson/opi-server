package br.edu.ufcg.dsc.opi.delegate;

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

	@ApiModelProperty(example = "5ecr3t0p153rv3r")
	@NotEmpty
	private String password;

	@ApiModelProperty(example = "DELEGATE")
	@NotEmpty
	private Roles role;

	@ApiModelProperty(example = "uhoaiuhskuiabhkuiybakf")
	private String jwt;

	@ApiModelProperty(example = "gauholiuhaolijshsldiui")
	private String refresh;

	public DelegateDTO() {
		this("blank", "blank");
	}

	public DelegateDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public DelegateDTO(String name, String email, String password, Roles role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public DelegateModel toModel() {
		return new DelegateModel(getName(), getEmail(), getPassword(), getRole());
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

	@Override
	public void setToken(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public void setRefreshToken(String refresh) {
		this.refresh = refresh;
	}

}
