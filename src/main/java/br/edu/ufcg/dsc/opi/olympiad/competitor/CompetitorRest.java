package br.edu.ufcg.dsc.opi.olympiad.competitor;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ufcg.dsc.opi.util.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = RestConstants.COMPETITOR_URI)
@Validated
@Api(tags = "Competitors")
public class CompetitorRest {

	@Autowired
	private CompetitorService competitorService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping({ "/", "" })
	@ApiOperation(value = "Create a Competitor", notes = "Also returns a link to retrieve the saved competitor in the location header")
	public ResponseEntity<Object> createCompetitor(@Valid @RequestBody CompetitorDTO competitor) {
		CompetitorModel savedCompetitor = competitorService.create(competitor.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCompetitor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping({ "/schools/{schoolId}/", "/schools/{schoolId}" })
	@ApiOperation(value = "Finds all Competititor by School")
	public Collection<CompetitorDTO> indexCompetitorsBySchool(@PathVariable("schoolId") Long schoolId) {
		return competitorService.indexBySchool(schoolId);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping({ "/{id}/", "/{id}" })
	@ApiOperation(value = "Updates a Competition")
	public ResponseEntity<?> updateCompetition(@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody CompetitorDTO competitor) {
		competitorService.update(id, competitor);
		return ResponseEntity.noContent().build();
	}

}
