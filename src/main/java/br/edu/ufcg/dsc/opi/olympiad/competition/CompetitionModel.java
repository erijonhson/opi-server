package br.edu.ufcg.dsc.opi.olympiad.competition;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Competition of the OPI.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_competition")
public class CompetitionModel implements Serializable {

	private static final long serialVersionUID = -862940921894335119L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "year", nullable = false, unique = true)
	private String year;

	@Column(name = "time_level_one")
	private Date timeLevelOne;

	@Column(name = "time_level_two")
	private Date timeLevelTwo;

	public CompetitionModel() {
		this(null, null, null);
	}

	public CompetitionModel(String year, Date timeLevelOne, Date timeLevelTwo) {
		this.year = year;
		this.timeLevelOne = timeLevelOne;
		this.timeLevelTwo = timeLevelTwo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getTimeLevelOne() {
		return timeLevelOne;
	}

	public void setTimeLevelOne(Date timeLevelOne) {
		this.timeLevelOne = timeLevelOne;
	}

	public Date getTimeLevelTwo() {
		return timeLevelTwo;
	}

	public void setTimeLevelTwo(Date timeLevelTwo) {
		this.timeLevelTwo = timeLevelTwo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CompetitionModel other = (CompetitionModel) obj;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OPI de " + year;
	}

}
