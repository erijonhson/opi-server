package br.edu.ufcg.dsc.opi.delegate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.UserDTO;
import br.edu.ufcg.dsc.opi.util.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

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
	@NotEmpty
	private Roles role;

	@ApiModelProperty(name = "jwt", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJleHAiOjE1MzAyODcyNzV9.orNXJJBUYC0Orcm-zMJULaPlcnwB9Iw7UwM532XVEYhclH624MzI32zKDIRTKHEYwtOPg_OJMQyin4itxs9MjA")
	private String jwt;

	@ApiModelProperty(name = "refresh", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJleHAiOjE1MzI0NDcyNzV9.2V48VZoDU3amGdJDKTJl4VuMlqGzCnZhCkD7UXFpUIUDnnvSM52oRS3UGq9Ri_hCtXkDeAUW7YfSws9JyuZLaw")
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

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	@ApiIgnore
	@ApiParam(value="dummy", hidden=true, required=false)
	@Override
	public void setToken(String jwt) {
		this.setJwt(jwt);
	}

	@ApiIgnore
	@ApiParam(value="dummy", hidden=true, required=false)
	@Override
	public void setRefreshToken(String refresh) {
		this.setRefresh(refresh);
	}

}
