package br.edu.ufcg.dsc.opi.admin;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to Admin.
 * 
 * @author Eri Jonhson
 */
@Repository(value = "adminRepository")
interface AdminRepository extends JpaRepository<AdminModel, Long> {

	public Collection<AdminModel> findByUserEmailContaining(String email);

	public AdminModel findByUserEmail(String email);

}
