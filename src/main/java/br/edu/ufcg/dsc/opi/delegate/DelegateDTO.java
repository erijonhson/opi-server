package br.edu.ufcg.dsc.opi.delegate;

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

@ApiModel(value = "Delegate")
public class DelegateDTO implements DTO<UserModel> {

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

	@ApiModelProperty(hidden = true)
	@JsonIgnore
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
	public UserModel toModel() {
		return UserFactory.createDelegateObject(getName(), getEmail(), getPassword(), getRoles());
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

	public static DelegateDTO toDTO(UserModel user) {
		return new DelegateDTO(user.getName(), user.getEmail(), null, user.getRoles());
	}

	public static Collection<DelegateDTO> toDTO(Collection<UserModel> users) {
		Collection<DelegateDTO> delegatesDTO = new HashSet<>();
		for (UserModel delegate : users) {
			delegatesDTO.add(DelegateDTO.toDTO(delegate));
		}
		return delegatesDTO;
	}

}
