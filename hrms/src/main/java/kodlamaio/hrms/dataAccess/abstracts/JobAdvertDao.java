package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Where ja.isActive='true'")
	List<JobAdvertWithEmployerWithJobTitleDto> getByIsActive();

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Where ja.isActive='true' and ja.employer.id=:employerId")
	List<JobAdvertWithEmployerWithJobTitleDto> getByIsActiveAndEmployerId(int employerId);

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Where ja.isActive='true' Order By ja.lastApplyDate Desc")
	List<JobAdvertWithEmployerWithJobTitleDto> getByLastApplyDate();

}
