package br.edu.ufcg.dsc.opi.school.student;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to SchoolStudent.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "schollStudentRepository")
interface StudentRepository extends JpaRepository<StudentModel, Long> {

	public Collection<StudentModel> findByAliasContaining(String alias);

	public StudentModel findByAlias(String alias);

}
