package br.edu.ufcg.dsc.opi.util.user;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to Simple User.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "userRepository")
interface UserRepository extends JpaRepository<UserModel, Long> {

	public Collection<UserModel> findByEmailContaining(String email);

	public UserModel findByEmail(String email);

}
