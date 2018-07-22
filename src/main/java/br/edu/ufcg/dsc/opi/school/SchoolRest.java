package br.edu.ufcg.dsc.opi.school;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ufcg.dsc.opi.util.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = RestConstants.SCHOOL_URI)
@Validated
@Api(tags = "Schools")
public class SchoolRest {

	@Autowired
	private SchoolService schoolService;

	/**
	 * Endpoint to create a School.
	 * 
	 * @param school to be register
	 * @return status
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'DELEGATE')")
	@PostMapping({ "/", "" })
	@ApiOperation(
		value = "Create a School", 
		notes = "Also returns a link to retrieve the saved school in the location header")
	public ResponseEntity<Object> createSchool(@Valid @RequestBody SchoolDTO school) {
		SchoolModel savedSchool = schoolService.create(school.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedSchool.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping({ "/", "" })
	@ApiOperation(value = "Index Schools")
	public Collection<SchoolDTO> indexSchool() {
		return schoolService.index();
	}

}
