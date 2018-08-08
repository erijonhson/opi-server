package br.edu.ufcg.dsc.opi.olympiad.competition;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ufcg.dsc.opi.util.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Competition")
public class CompetitionDTO implements DTO<CompetitionModel> {

	@ApiModelProperty(example = "2018", required = true)
	@NotEmpty
	private String year;

	@ApiModelProperty(example = "2018-02-30T00:00:00.00Z", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	private Instant timeLevelOne;

	@ApiModelProperty(example = "2018-06-30T00:00:00.00Z", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	private Instant timeLevelTwo;

	public CompetitionDTO() {
		this("", null, null);
	}

	public CompetitionDTO(String year, Instant timeLevelOne, Instant timeLevelTwo) {
		this.year = year;
		this.timeLevelOne = timeLevelOne;
		this.timeLevelTwo = timeLevelTwo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Instant getTimeLevelOne() {
		return timeLevelOne;
	}

	public void setTimeLevelOne(Instant timeLevelOne) {
		this.timeLevelOne = timeLevelOne;
	}

	public Instant getTimeLevelTwo() {
		return timeLevelTwo;
	}

	public void setTimeLevelTwo(Instant timeLevelTwo) {
		this.timeLevelTwo = timeLevelTwo;
	}

	@Override
	public CompetitionModel toModel() {
		// @formatter:off
		return new CompetitionModel(
				getYear(), 
				timeLevelOne != null ? new Date(timeLevelOne.toEpochMilli()) : null,
				timeLevelTwo != null ? new Date(timeLevelTwo.toEpochMilli()) : null);
		// @formatter:on
	}

	public static CompetitionDTO toDTO(CompetitionModel competition) {
		// @formatter:off
		return new CompetitionDTO(competition.getYear(), 
				competition.getTimeLevelOne() != null ? competition.getTimeLevelOne().toInstant() : null,
				competition.getTimeLevelTwo() != null ? competition.getTimeLevelTwo().toInstant() : null);
		// @formatter:on
	}

	public static Collection<CompetitionDTO> toDTO(Collection<CompetitionModel> competitions) {
		Collection<CompetitionDTO> competitionsDTO = new HashSet<>();
		for (CompetitionModel competition : competitions) {
			competitionsDTO.add(CompetitionDTO.toDTO(competition));
		}
		return competitionsDTO;
	}

}
