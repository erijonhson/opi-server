package br.edu.ufcg.dsc.opi.school.student;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import br.edu.ufcg.dsc.opi.util.user.UserService;

/**
 * Business logic layer to SchoolStudent.
 * 
 * @author Eri Jonhson
 */
@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired(required = true)
	private StudentRepository schoolStudentRepository;

	@Autowired(required = true)
	private UserService userService;

	@Override
	@Transactional
	public StudentModel create(StudentModel student) {
		student.setRoles(EnumSet.of(Roles.ROLE_SCHOOL_STUDENT));
		UserModel user = userService.create(student.getUser());
		student.setUser(user);
		return schoolStudentRepository.save(student);
	}

	@Override
	public List<String> createAll(List<StudentModel> students) {
		List<String> status = new LinkedList<>();
		for (StudentModel student : students) {
			try {
				this.create(student);
				status.add("OK");
			} catch (Exception e) {
				// TODO: internacionalization?
				status.add(e.getMessage());
			}
		}
		return status;
	}

	@Override
	public Collection<StudentDTO> indexByAlias(String alias) {
		Collection<StudentModel> schoolStudents = schoolStudentRepository.findByAliasContaining(alias);
		return StudentDTO.toDTO(schoolStudents);
	}

	@Override
	public StudentDTO showByAlias(String alias) {
		StudentModel studentModel = schoolStudentRepository.findByAlias(alias);
		return StudentDTO.toDTO(studentModel);
	}

}
