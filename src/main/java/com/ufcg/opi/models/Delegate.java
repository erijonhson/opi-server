package com.ufcg.opi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Delegate responsible for one or more schools.
 * 
 * @author Eri Jonhson
 */
@Entity
@Table(name = "tb_delegate")
public class Delegate implements Serializable {

	private static final long serialVersionUID = -4762740963019321048L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Size(min=3, max=256)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	public Delegate() {
		this("blank", "blank");
	}

	public Delegate(String name, String email) {
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Delegate))
			return false;
		Delegate delegate = (Delegate) obj;
		if (email == null) {
			if (delegate.email != null)
				return false;
		} else if (!email.equals(delegate.email))
			return false;
		if (name == null) {
			if (delegate.name != null)
				return false;
		} else if (!name.equals(delegate.name))
			return false;
		return true;
	}

}
