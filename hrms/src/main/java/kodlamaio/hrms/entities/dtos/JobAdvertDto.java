package kodlamaio.hrms.entities.dtos;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertDto {

	private String companyName;
	
	private String jobTitle;
	
	private int openPositionCount;
	
	private String createDate;
	
	private String lastApplyDate;
}
