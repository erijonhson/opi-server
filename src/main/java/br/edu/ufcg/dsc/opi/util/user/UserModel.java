package br.edu.ufcg.dsc.opi.util.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufcg.dsc.opi.security.Roles;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Simple User.
 * 
 * @author Eri Jonhson
 */
@ApiIgnore
@Entity
@Table(name = "tb_user")
@EntityListeners(UserModelListener.class)
public class UserModel implements Serializable, UserDetails {

	private static final long serialVersionUID = -2449991389290724038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Size(min = 3, max = 256, message = "Nome deve ter entre 3 e 256 caracteres")
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Roles> roles;

	@Column(name = "locked", nullable = false, columnDefinition = "tinyint(1) default 0")
	private boolean locked = true;

	@Column(name = "enabled", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean enabled = true;

	@Column(name = "expired", nullable = false, columnDefinition = "tinyint(1) default 0")
	private boolean expired = false;

	@Column(name = "credentials_expired", nullable = false, columnDefinition = "tinyint(1) default 0")
	private boolean credentialsExpired = false;

	protected UserModel() {
		this("blank", "blank@blank.com", "blank", null);
	}

	protected UserModel(String name, String email, String password, Set<Roles> roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	protected UserModel(Long id, Set<Roles> roles) {
		this();
		this.id = id;
	}

	protected UserModel(Set<Roles> roles) {
		this();
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

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

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserModel))
			return false;
		UserModel userModel = (UserModel) obj;
		if (email == null) {
			if (userModel.email != null)
				return false;
		} else if (!email.equals(userModel.email))
			return false;
		if (name == null) {
			if (userModel.name != null)
				return false;
		} else if (!name.equals(userModel.name))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] roles = new String[this.roles.size()];
		int index = 0;
		for (Roles role : this.roles) {
			roles[index++] = role.toString();
		}
		return AuthorityUtils.createAuthorityList(roles);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
