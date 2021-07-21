package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {
	private int schoolId;
	private int resumeId;
	private String schoolName;
	private String schoolEpisode;
	private LocalDate startDate;
	private LocalDate endDate;

}
