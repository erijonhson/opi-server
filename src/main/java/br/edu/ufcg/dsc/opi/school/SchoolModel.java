package br.edu.ufcg.dsc.opi.school;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import br.edu.ufcg.dsc.opi.olympiad.OpiCategory;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Partner school of the OPI.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_school")
public class SchoolModel implements Serializable {

	private static final long serialVersionUID = -178445149567395447L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty
	@Column(name = "city", nullable = false)
	private String city;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "school")
	private Set<SchoolPhoneNumber> schoolPhoneNumbers;

	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserModel delegate;

	@ElementCollection(targetClass = OpiCategory.class)
	@CollectionTable(name = "tb_opi_category", joinColumns = @JoinColumn(name = "school_id"))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<OpiCategory> categories;

	public SchoolModel() {
		this("blank", "blank", new HashSet<>(), UserFactory.createDelegateObject(), new HashSet<>());
	}

	public SchoolModel(String name, String city, Set<SchoolPhoneNumber> schoolPhoneNumbers, UserModel delegate, Set<OpiCategory> categories) {
		this.name = name;
		this.city = city;
		this.schoolPhoneNumbers = schoolPhoneNumbers;
		this.delegate = delegate;
		this.categories = categories;
		
		for (SchoolPhoneNumber number : this.schoolPhoneNumbers) {
			number.setSchool(this);
		}
	}

	public SchoolModel(Long id) {
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public UserModel getDelegate() {
		return delegate;
	}

	public void setDelegate(UserModel delegate) {
		this.delegate = delegate;
	}

	public Set<SchoolPhoneNumber> getSchoolPhoneNumbers() {
		return schoolPhoneNumbers;
	}

	public Set<OpiCategory> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolModel other = (SchoolModel) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
