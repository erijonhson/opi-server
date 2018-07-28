package br.edu.ufcg.dsc.opi.school.student;

import java.time.Instant;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.DTO;
import br.edu.ufcg.dsc.opi.util.user.Gender;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolStudent")
public class StudentDTO implements DTO<StudentModel> {

	@ApiModelProperty(example = "Zé da Silva", required = true)
	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	private String name;

	@ApiModelProperty(example = "ze@silva.com", required = false)
	@Email
	private String email;

	@ApiModelProperty(example = "abcde", required = false)
	@NotEmpty
	private String password;

	@ApiModelProperty(example = "[ROLE_SCHOOL_STUDENT]", required = false)
	private Set<Roles> roles;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String token;

	@ApiModelProperty(example = "ze.silva", required = true)
	@NotEmpty
	@Size(min = 3, max = 45, message = "Apelido deve ter entre 3 e 45 caracteres")
	private String alias;

	@ApiModelProperty(example = "1990-02-30T00:00:00.00Z", required = true)
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	private Instant dateOfBirth;

	@ApiModelProperty(example = "FEMALE", required = true)
	private Gender gender;

	@ApiModelProperty(example = "1", required = true)
	private Long schoolId;

	// @formatter:off
	public StudentDTO() {
		this("blank", "blank", "blank", EnumSet.of(Roles.ROLE_SCHOOL_STUDENT), "blank", Instant.now(), Gender.OTHER, 0L);
	}
	// @formatter:on

	public StudentDTO(String name, String email, String password, Set<Roles> roles, String alias, Instant dateOfBirth,
			Gender gender, Long schoolId) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.alias = alias;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.schoolId = schoolId;
	}

	// @formatter:off
	@Override
	public StudentModel toModel() {
		return UserFactory.createSchoolStudenteObject(getName(), getEmail(), getPassword(), getRoles(),
				getAlias(), getDateOfBirth(), getGender(), getSchoolId());
	}
	// @formatter:on

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Instant getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public static StudentDTO toDTO(final StudentModel schoolStudent) {
		if (schoolStudent == null)
			return null;
		return new StudentDTO(schoolStudent.getName(), schoolStudent.getEmail(), null, schoolStudent.getRoles(),
				schoolStudent.getAlias(), schoolStudent.getDateOfBirth().toInstant(), schoolStudent.getGender(),
				schoolStudent.getSchool().getId());
	}

	public static Collection<StudentDTO> toDTO(final Collection<StudentModel> schoolStudents) {
		Collection<StudentDTO> schoolStudentsDTO = new HashSet<>();
		for (StudentModel schoolStudent : schoolStudents) {
			schoolStudentsDTO.add(StudentDTO.toDTO(schoolStudent));
		}
		return schoolStudentsDTO;
	}

	public static List<StudentModel> toModel(final List<StudentDTO> schoolStudents) {
		List<StudentModel> schoolStudentsModels = new LinkedList<>();
		for (StudentDTO studentDTO : schoolStudents) {
			schoolStudentsModels.add(studentDTO.toModel());
		}
		return schoolStudentsModels;
	}

}
