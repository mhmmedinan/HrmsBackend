package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto {

	private int id;

	private int openPositionCount;

	private LocalDate createDate;

	private LocalDate lastApplyDate;

	private String companyName;

	private String title;
	
	private String city;
	
	private String educationLevel;
	
	private String working;
}
