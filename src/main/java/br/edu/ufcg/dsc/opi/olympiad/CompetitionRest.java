package br.edu.ufcg.dsc.opi.olympiad;

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
@RequestMapping(value = RestConstants.COMPETITION_URI)
@Validated
@Api(tags = "Competitions")
public class CompetitionRest {

	@Autowired
	private CompetitionService competitionService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping({ "/", "" })
	@ApiOperation(value = "Create a Competition", notes = "Also returns a link to retrieve the saved competition in the location header")
	public ResponseEntity<Object> createCompetition(@Valid @RequestBody CompetitionDTO competition) {
		CompetitionModel savedCompetition = competitionService.create(competition.toModel());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCompetition.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping({ "/", "" })
	@ApiOperation(value = "Finds all Competitions")
	public Collection<CompetitionDTO> indexCompetitions() {
		return competitionService.index();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping({ "/{id}/", "/{id}" })
	@ApiOperation(value = "Updates a Competition")
	public ResponseEntity<?> updateCompetition(
			@Min(value=1) @PathVariable("id") Long id,
			@Valid @RequestBody CompetitionDTO competition) {
		competitionService.update(id, competition);
		return ResponseEntity.noContent().build();
	}

}
