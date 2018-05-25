package br.edu.ufcg.dsc.opi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import springfox.documentation.annotations.ApiIgnore;

/**
 * School phone numbers.
 * 
 * @author Eri Jonhson
 */
@Entity
@Table(name = "tb_school_phone_number")
@ApiIgnore
public class SchoolPhoneNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "number", nullable = false, unique = true)
	private String number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	public SchoolPhoneNumber() {
		this("blank");
	}

	public SchoolPhoneNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
