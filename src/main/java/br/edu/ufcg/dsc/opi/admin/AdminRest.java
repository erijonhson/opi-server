package br.edu.ufcg.dsc.opi.admin;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.collect.ImmutableMap;

import br.edu.ufcg.dsc.opi.security.AccountCredentials;
import br.edu.ufcg.dsc.opi.security.Roles;
import br.edu.ufcg.dsc.opi.security.SecurityUtils;
import br.edu.ufcg.dsc.opi.util.RestConstants;
import br.edu.ufcg.dsc.opi.util.user.UserModel;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = RestConstants.ADMIN_URI)
@Validated
public class AdminRest {

	@Autowired
	private AdminServiceImpl adminService;

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping({ "/", "" })
	@ApiOperation(value = "Create an Admin", notes = "Also returns a link to retrieve the saved admin in the location header")
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody AdminDTO admin) {
		UserModel savedAdmin = adminService.create(admin.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAdmin.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping({ "/", "" })
	@ApiOperation(value = "Finds an Admin by e-mail")
	public Collection<AdminDTO> indexAdminByEmail(@Size(min = 5, max = 256) @RequestParam String email) {
		return adminService.indexByEmail(email);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping({ "/locking/", "/locking" })
	@ApiOperation(value = "Manipulate locking of an user account")
	public ResponseEntity<Object> changingLocking(@Size(min = 5, max = 256) @Email @RequestParam String userEmail, @RequestParam boolean status) {
		if (adminService.changingLocking(userEmail, status)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping({ "/enabling/", "/enabling" })
	@ApiOperation(value = "Manipulate enabling of an user account")
	public ResponseEntity<Object> changingEnabling(@Size(min = 5, max = 256) @Email @RequestParam String userEmail, @RequestParam boolean status) {
		if (adminService.changingEnabling(userEmail, status)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping({ "/roles/", "/roles" })
	@ApiOperation(value = "Manipulate roles of an user account")
	public ResponseEntity<Object> changingRoles(@Size(min = 5, max = 256) @Email @RequestParam String userEmail, @RequestParam Set<Roles> roles) {
		if (adminService.changingRoles(userEmail, roles)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping({ "/login/", "/login" })
	@ApiOperation(value = "Login an Admin")
	public ResponseEntity<AdminDTO> loginAdmin(@RequestBody AccountCredentials accountCredentials) {
		AdminDTO adminDTO = adminService.login(
				accountCredentials.getUsername(),
				accountCredentials.getPassword());

		if (adminDTO == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok()
				.headers(SecurityUtils.fillAccessControlHeader(ImmutableMap.of(SecurityUtils.TOKEN_HEADER, adminDTO.getToken())))
				.body(adminDTO);
	}

}
