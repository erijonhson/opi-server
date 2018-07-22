package br.edu.ufcg.dsc.opi.util.user;

import java.time.Instant;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import br.edu.ufcg.dsc.opi.school.SchoolModel;
import br.edu.ufcg.dsc.opi.school.student.StudentModel;
import br.edu.ufcg.dsc.opi.security.Payload;
import br.edu.ufcg.dsc.opi.security.Roles;

/**
 * @see Roles
 * @author Eri Jonhson
 */
public class UserFactory {

	private UserFactory() {
	}

	// Security

	public static Payload createPayload(UserModel user) {
		// @formatter:off
		return new Payload(
				user.getEmail(),
				user.getRoles().toArray(new Roles[0]),
				user.isEnabled(),
				!user.isAccountNonLocked());
		// @formatter:on
	}

	// Admin

	public static UserModel createAdminObject(String name, String email, String password, Set<Roles> roles) {
		UserModel adminUser = new UserModel(name, email, password, roles);
		adminUser.setEnabled(true);
		adminUser.setLocked(false);
		return adminUser;
	}

	public static UserModel createAdminObject(String name, String email, String password) {
		UserModel adminUser = new UserModel(name, email, password, EnumSet.of(Roles.ROLE_ADMIN));
		adminUser.setEnabled(true);
		adminUser.setLocked(false);
		return adminUser;
	}

	// Delegate

	public static UserModel createDelegateObject(String name, String email, String password, Set<Roles> roles) {
		UserModel delegateUser = new UserModel(name, email, password, roles);
		return delegateUser;
	}

	public static UserModel createDelegateObject(String name, String email, String password) {
		UserModel delegateUser = new UserModel(name, email, password, EnumSet.of(Roles.ROLE_DELEGATE));
		return delegateUser;
	}

	public static UserModel createDelegateObject() {
		UserModel delegateUser = new UserModel(EnumSet.of(Roles.ROLE_DELEGATE));
		return delegateUser;
	}

	public static UserModel createDelegateObject(Long id) {
		UserModel delegateUser = new UserModel(id, EnumSet.of(Roles.ROLE_DELEGATE));
		return delegateUser;
	}

	// Student

	// @formatter:off

	public static StudentModel createSchoolStudenteObject(String name, String email, String password, 
			String alias, Date dateOfBirth, Gender gender, Long schoolId) {
		UserModel user = new UserModel(name, email, password, EnumSet.of(Roles.ROLE_SCHOOL_STUDENT));
		SchoolModel school = new SchoolModel(schoolId);
		StudentModel schoolStudentUser = new StudentModel(user, alias, dateOfBirth, gender, school);
		return schoolStudentUser;
	}

	public static StudentModel createSchoolStudenteObject(String name, String email, String password, Set<Roles> roles, 
			String alias, Instant dateOfBirth, Gender gender, Long schoolId) {
		UserModel user = new UserModel(name, email, password, roles);
		SchoolModel school = new SchoolModel(schoolId);
		StudentModel schoolStudentUser = new StudentModel(user, alias, new Date(dateOfBirth.toEpochMilli()), gender, school);
		return schoolStudentUser;
	}
	// @formatter:on

	public static UserModel createUserNullObject() {
		return new UserModel();
	}
}
