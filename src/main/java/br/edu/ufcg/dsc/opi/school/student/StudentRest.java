package br.edu.ufcg.dsc.opi.school.student;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ufcg.dsc.opi.util.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = RestConstants.SCHOOL_STUDANTS_URI)
@Validated
@Api(tags = "Students")
@CrossOrigin
public class StudentRest {

	@Autowired
	private StudentService studentService;

	@PreAuthorize("hasAnyRole('ADMIN', 'DELEGATE', 'COLLABORATOR')")
	@PostMapping({ "/", "" })
	@ApiOperation(value = "Create a School Student", notes = "Also returns a link to retrieve the saved school student in the location header")
	public ResponseEntity<Object> createSchoolStudent(@Valid @RequestBody StudentDTO student) {
		StudentModel savedStudent = studentService.create(student.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'DELEGATE', 'COLLABORATOR')")
	@PostMapping({ "/all", "all" })
	@ApiOperation(value = "Also returns a list of messages with 'OK', case create has been success, or message error, case otherwise")
	public List<String> createAllSchoolStudent(@Valid @RequestBody List<StudentDTO> students) {
		return studentService.createAll(StudentDTO.toModel(students));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'DELEGATE', 'COLLABORATOR')")
	@GetMapping({ "/", "" })
	@ApiOperation(value = "Finds a School Student by alias")
	public Collection<StudentDTO> indexSchoolStudentByAlias(@Size(min = 3, max = 45) @RequestParam String alias) {
		return studentService.indexByAlias(alias);
	}

}
