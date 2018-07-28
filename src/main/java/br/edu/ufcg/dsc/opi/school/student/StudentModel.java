package br.edu.ufcg.dsc.opi.school.student;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import br.edu.ufcg.dsc.opi.school.SchoolModel;
import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.user.Gender;
import br.edu.ufcg.dsc.opi.util.user.User;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Student of the School.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_school_student")
public class StudentModel implements Serializable, User {

	private static final long serialVersionUID = 5540971989246092210L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserModel user;

	@NotEmpty
	@Size(min = 3, max = 45, message = "Apelido deve ter entre 3 e 45 caracteres")
	@Column(name = "alias")
	private String alias;

	@Past
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "school_id", nullable = false)
	private SchoolModel school;

	public StudentModel() {
		this(UserFactory.createUserNullObject(), "blank", null, null, new SchoolModel());
	}

	public StudentModel(UserModel user, String alias, Date dateOfBirth, Gender gender, SchoolModel school) {
		this.user = user;
		this.alias = alias;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.school = school;
	}

	public Long getId() {
		return id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public SchoolModel getSchool() {
		return school;
	}

	public void setSchool(SchoolModel school) {
		this.school = school;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public void setName(String name) {
		user.setName(name);
	}

	@Override
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public void setEmail(String email) {
		user.setEmail(email);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public void setPassword(String password) {
		user.setPassword(password);
	}

	@Override
	public Set<Roles> getRoles() {
		return user.getRoles();
	}

	@Override
	public void setRoles(Set<Roles> roles) {
		user.setRoles(roles);
	}

	@Override
	public void setLocked(boolean locked) {
		user.setLocked(locked);
	}

	@Override
	public void setEnabled(boolean enabled) {
		user.setEnabled(enabled);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
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
		StudentModel other = (StudentModel) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

}
