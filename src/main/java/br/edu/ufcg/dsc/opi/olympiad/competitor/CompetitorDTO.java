package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.constraints.Min;

import br.edu.ufcg.dsc.opi.school.Grades;
import br.edu.ufcg.dsc.opi.school.student.StudentModel;
import br.edu.ufcg.dsc.opi.util.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Competitor")
public class CompetitorDTO implements DTO<CompetitorModel> {

	@ApiModelProperty(example = "FIRST_HIGH")
	private Grades grade;

	@Min(value = 0)
	@ApiModelProperty(example = "7")
	private Integer markLevelOne;

	@Min(value = 0)
	@ApiModelProperty(example = "5")
	private Integer markLevelTwo;

	@Min(value = 1)
	@ApiModelProperty(example = "1", required = true)
	private Long studentId;

	public CompetitorDTO() {
		this(null, 0, 0, 0);
	}

	public CompetitorDTO(Grades grade, int markLevelOne, int markLevelTwo, long studentId) {
		this.grade = grade;
		this.markLevelOne = markLevelOne;
		this.markLevelTwo = markLevelTwo;
		this.studentId = studentId;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Override
	public CompetitorModel toModel() {
		StudentModel student = new StudentModel(studentId);
		return new CompetitorModel(student, getGrade(), getMarkLevelOne(), getMarkLevelTwo());
	}

	public static CompetitorDTO toDTO(final CompetitorModel competitor) {
		if (competitor == null)
			return null;
		return new CompetitorDTO(competitor.getGrade(), competitor.getMarkLevelOne(), competitor.getMarkLevelTwo(),
				competitor.getId());
	}

	public static Collection<CompetitorDTO> toDTO(final Collection<CompetitorModel> competitors) {
		Collection<CompetitorDTO> competitorsDTO = new HashSet<>();
		for (CompetitorModel competitor : competitors) {
			competitorsDTO.add(CompetitorDTO.toDTO(competitor));
		}
		return competitorsDTO;
	}

}
