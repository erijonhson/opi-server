package br.edu.ufcg.dsc.opi.school.student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

	public StudentModel create(StudentModel student);

	public List<String> createAll(List<StudentModel> students);

	public Collection<StudentDTO> indexByAlias(String alias);

	public StudentDTO showByAlias(String alias);

}
