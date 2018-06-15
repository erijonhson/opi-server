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

import br.edu.ufcg.dsc.opi.delegate.DelegateModel;
import br.edu.ufcg.dsc.opi.olympiad.OpiCategory;

/**
 * Partner school of the OPI.
 * 
 * @author Eri Jonhson
 */
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
	@JoinColumn(name = "delegate_id")
	private DelegateModel delegate;

	@ElementCollection(targetClass = OpiCategory.class)
	@CollectionTable(name = "tb_opi_category", joinColumns = @JoinColumn(name = "school_id"))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<OpiCategory> categories;

	public SchoolModel() {
		this("blank", "blank", new HashSet<>(), new DelegateModel(), new HashSet<>());
	}

	public SchoolModel(String name, String city, Set<SchoolPhoneNumber> schoolPhoneNumbers, DelegateModel delegate, Set<OpiCategory> categories) {
		this.name = name;
		this.city = city;
		this.schoolPhoneNumbers = schoolPhoneNumbers;
		this.delegate = delegate;
		this.categories = categories;
		
		for (SchoolPhoneNumber number : this.schoolPhoneNumbers) {
			number.setSchool(this);
		}
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

	public DelegateModel getDelegate() {
		return delegate;
	}

	public void setDelegate(DelegateModel delegate) {
		this.delegate = delegate;
	}

	public Set<SchoolPhoneNumber> getSchoolPhoneNumbers() {
		return schoolPhoneNumbers;
	}

	public Set<OpiCategory> getCategories() {
		return categories;
	}

}
