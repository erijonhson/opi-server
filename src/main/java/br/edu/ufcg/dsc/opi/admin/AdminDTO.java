package br.edu.ufcg.dsc.opi.admin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.DTO;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Admin")
public class AdminDTO implements DTO<UserModel> {

	@ApiModelProperty(example = "Rohit Gheyi", required = true)
	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	private String name;

	@ApiModelProperty(example = "rohit@rohit.com", required = true)
	@NotEmpty
	@Email
	private String email;

	@ApiModelProperty(example = "abcde", required = true)
	@NotEmpty
	private String password;

	@ApiModelProperty(example = "[ROLE_ADMIN]")
	private Set<Roles> roles;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String token;

	public AdminDTO() {
		this("blank", "blank");
	}

	public AdminDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public AdminDTO(String name, String email, String password, Set<Roles> roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public UserModel toModel() {
		UserModel user = UserFactory.createAdminObject(getName(), getEmail(), getPassword(), getRoles());
		user.setEnabled(true);
		user.setLocked(false);
		return user;
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

	public void setToken(String token) {
		this.token = token;
	}

	public static AdminDTO toDTO(UserModel user) {
		return new AdminDTO(user.getName(), user.getEmail(), null, user.getRoles());
	}

	public static Collection<AdminDTO> toDTO(Collection<UserModel> users) {
		Collection<AdminDTO> delegatesDTO = new HashSet<>();
		for (UserModel delegate : users) {
			delegatesDTO.add(AdminDTO.toDTO(delegate));
		}
		return delegatesDTO;
	}

}
