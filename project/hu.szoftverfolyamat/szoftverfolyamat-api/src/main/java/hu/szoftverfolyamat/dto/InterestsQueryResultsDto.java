package hu.szoftverfolyamat.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InterestsQueryResultsDto {
	private List<InterestsQueryResultDto> results;
}
