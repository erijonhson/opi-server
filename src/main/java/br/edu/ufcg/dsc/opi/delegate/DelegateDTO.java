package br.edu.ufcg.dsc.opi.delegate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ufcg.dsc.opi.util.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Delegate")
public class DelegateDTO implements DTO<DelegateModel> {

	@ApiModelProperty(example = "Rohit Gheyi")
	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	private String name;

	@ApiModelProperty(example = "rohit@rohit.com")
	@NotEmpty
	@Email
	private String email;

	public DelegateDTO() {
		this("blank", "blank");
	}

	public DelegateDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public DelegateModel toModel() {
		return new DelegateModel(name, email);
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

}
