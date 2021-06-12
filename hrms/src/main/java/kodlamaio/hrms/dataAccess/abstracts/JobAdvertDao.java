package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.working) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa   Where ja.isActive='true'")
	List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto> getByIsActive();

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.working) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa   Where ja.isActive='true' and ja.employer.id=:employerId")
	List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto> getByIsActiveAndEmployerId(int employerId);

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.working) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa Where ja.isActive='true' Order By ja.createDate Desc")
	List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto> getByLastApplyDate();

}
