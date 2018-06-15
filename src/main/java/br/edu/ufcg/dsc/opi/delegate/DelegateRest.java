package br.edu.ufcg.dsc.opi.delegate;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ufcg.dsc.opi.util.RestConstants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = RestConstants.DELEGATE_URI)
@Validated
public class DelegateRest {

	@Autowired
	private DelegateService delegateService;

	/**
	 * Endpoint to create a Delegate.
	 * 
	 * @param delegate to be created
	 * @return status
	 */
	@PostMapping({ "/", "" })
	@ApiOperation(
		value = "Create a Delegate", 
		notes = "Also returns a link to retrieve the saved delegate in the location header")
	public ResponseEntity<Object> createDelegate(@Valid @RequestBody DelegateDTO delegate) {
		DelegateModel savedDelegate = delegateService.create(delegate.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDelegate.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping({ "/", "" })
	@ApiOperation(value = "Finds a Delegate by e-mail")
	public Collection<DelegateDTO> indexDelegateByEmail(@Size(min=5, max=256) @RequestParam String email) {
		return delegateService.indexByEmail(email);
	}

}
