package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ufcg.dsc.opi.school.Grades;
import br.edu.ufcg.dsc.opi.school.student.StudentModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Competitor of the OPI.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_competitor")
public class CompetitorModel implements Serializable {

	private static final long serialVersionUID = -674978064483595404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "student_id")
	private StudentModel student;

	@Enumerated(EnumType.STRING)
	private Grades grade;

	@Column(name = "mark_level_one")
	private Integer markLevelOne = -1;

	@Column(name = "mark_level_two")
	private Integer markLevelTwo = -1;

	public CompetitorModel(StudentModel student, Grades grade, int markLevelOne, int markLevelTwo) {
		this.student = student;
		this.grade = grade;
		this.markLevelOne = markLevelOne;
		this.markLevelTwo = markLevelTwo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	public Grades getGrade() {
		return grade;
	}

	public void setGrade(Grades grade) {
		this.grade = grade;
	}

	public Integer getMarkLevelOne() {
		return markLevelOne;
	}

	public void setMarkLevelOne(Integer markLevelOne) {
		this.markLevelOne = markLevelOne;
	}

	public Integer getMarkLevelTwo() {
		return markLevelTwo;
	}

	public void setMarkLevelTwo(Integer markLevelTwo) {
		this.markLevelTwo = markLevelTwo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		CompetitorModel other = (CompetitorModel) obj;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

}
