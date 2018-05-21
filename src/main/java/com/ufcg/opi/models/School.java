package com.ufcg.opi.models;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Partner school of the OPI.
 * 
 * @author Eri Jonhson
 */
@Entity
@Table(name = "tb_school")
public class School implements Serializable {

	private static final long serialVersionUID = -178445149567395447L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "city", nullable = false)
	private String city;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "school")
	private Set<SchoolPhoneNumber> schoolPhoneNumbers;

	@OneToOne(fetch = FetchType.EAGER)
    @MapsId
	private Delegate delegate;

	@ElementCollection(targetClass = OpiCategory.class)
	@CollectionTable(name = "tb_opi_category", joinColumns = @JoinColumn(name = "school_id"))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<OpiCategory> categories;

	public School() {
		this("blank", "blank", new HashSet<>(), new Delegate(), new HashSet<>());
	}

	public School(String name, String city, Set<SchoolPhoneNumber> schoolPhoneNumbers, Delegate delegate, Set<OpiCategory> categories) {
		this.name = name;
		this.city = city;
		this.schoolPhoneNumbers = schoolPhoneNumbers;
		this.delegate = delegate;
		this.categories = categories;
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

	public Delegate getDelegate() {
		return delegate;
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
