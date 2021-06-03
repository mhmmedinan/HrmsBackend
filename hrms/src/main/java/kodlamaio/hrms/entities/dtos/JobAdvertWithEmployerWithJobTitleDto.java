package kodlamaio.hrms.entities.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertWithEmployerWithJobTitleDto {

	private int id;

	private int openPositionCount;

	private LocalDateTime createDate;

	private Date lastApplyDate;

	private String companyName;

	private String title;

}
