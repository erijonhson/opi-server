package br.edu.ufcg.dsc.opi.school;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import br.edu.ufcg.dsc.opi.olympiad.OpiCategory;
import br.edu.ufcg.dsc.opi.util.DTO;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="School")
public class SchoolDTO implements DTO<SchoolModel> {

	@ApiModelProperty(example = "Universidade Federal de Campina Grande", required = true)
	@NotEmpty(message = "Colégio deve ter um nome")
	private String name;

	@ApiModelProperty(example = "Campina Grande", required = true)
	@NotEmpty(message = "Colégio deve ter uma cidade")
	private String city;

	@ApiModelProperty(example = "['+55 83 3310 1122', '+55 83 3310 1027', '+55 83 3310 1124']", required = true)
	@NotEmpty(message = "Colégio deve ter ao menos um número para contato")
	private Set<String> phoneNumbers;

	@ApiModelProperty(example = "1", required = true)
	private Long delegateId;

	@ApiModelProperty(
			dataType = "string", 
			allowableValues = "Iniciação_1, Iniciação_1_Pub, Iniciação_2, Iniciação_2_Pub, Programação, Avançado_Júnior, Avançado_Sênior",
			example = "['Iniciação_1', 'Iniciação_1_Pub', 'Iniciação_2', 'Iniciação_2_Pub', 'Programação', 'Avançado_Júnior', 'Avançado_Sênior']")
	@NotEmpty(message = "Colégio deve querer aplicar a OPI para ao menos uma categoria")
	private Set<OpiCategory> categories;

	public SchoolDTO() {
		this(null, null, null, null, null);
	}

	public SchoolDTO(String name, String city, UserModel delegate, Set<SchoolPhoneNumber> phoneNumbers, Set<OpiCategory> categories) {
		this.name = name != null ? name : "blank";
		this.city = city != null ? city : "blank";
		this.delegateId = delegate != null ? delegate.getId() : 0L;
		this.phoneNumbers = new HashSet<>();
		if (phoneNumbers != null) {
			for (SchoolPhoneNumber phone : phoneNumbers) {
				this.phoneNumbers.add(phone.getNumber());
			}
		}
		this.categories = categories != null ? categories : new HashSet<>();
	}

	public SchoolDTO(String name, String city, long delegateId, Set<String> phoneNumbers, Set<OpiCategory> categories) {
		this.name = name != null ? name : "blank";
		this.city = city != null ? city : "blank";
		this.delegateId = delegateId;
		this.phoneNumbers = phoneNumbers;
		this.categories = categories != null ? categories : new HashSet<>();
	}

	@Override
	public SchoolModel toModel() {
		UserModel delegate = UserFactory.createDelegateObject(delegateId);
		Set<SchoolPhoneNumber> schoolPhoneNumbers = new HashSet<>();
		for (String s : phoneNumbers) {
		    schoolPhoneNumbers.add(new SchoolPhoneNumber(s));
		}
		return new SchoolModel(name, city, schoolPhoneNumbers, delegate, categories);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Long getDelegateId() {
		return delegateId;
	}

	public void setDelegateId(Long delegateId) {
		this.delegateId = delegateId;
	}

	public Set<OpiCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<OpiCategory> categories) {
		this.categories = categories;
	}

}
