package br.edu.ufcg.dsc.opi.admin;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.util.user.User;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Admin responsible for everything.
 * 
 * @author Eri Jonhson
 */
@Entity
@Table(name = "tb_admin")
@ApiIgnore
public class AdminModel implements Serializable, User<AdminDTO> {

	private static final long serialVersionUID = -4762740963019321048L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne
    @JoinColumn(name = "user_id")
	private UserModel user;

	public AdminModel() {
		this("blank", "blank@blank.com", "blank", EnumSet.of(Roles.ADMIN));
	}

	public AdminModel(String name, String email, String password, Set<Roles> roles) {
		if (roles == null) roles = EnumSet.of(Roles.ADMIN);
		this.user = new UserModel(name, email, password, roles);
		this.user.setEnabled(true);
		this.user.setLocked(false);
	}

	public AdminModel(Long id) {
		this.id = id;
		this.user.setEnabled(true);
		this.user.setLocked(false);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		user.setName(name);
	}

	public String getEmail() {
		return user.getEmail();
	}

	public void setEmail(String email) {
		user.setEmail(email);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}
	
	public Set<Roles> getRoles() {
		return user.getRoles();
	}

	public void setRoles(Set<Roles> roles) {
		user.setRoles(roles);
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
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
		return isEnabled();
	}

	@Override
	public AdminDTO toDTO() {
		return new AdminDTO(getName(), getEmail(), getPassword(), getRoles());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AdminModel))
			return false;
		AdminModel admin = (AdminModel) obj;
		if (getEmail() == null) {
			if (getEmail() != null)
				return false;
		} else if (!getEmail().equals(admin.getEmail()))
			return false;
		if (getName() == null) {
			if (admin.getName() != null)
				return false;
		} else if (!getName().equals(admin.getName()))
			return false;
		return true;
	}

}
