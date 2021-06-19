package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.workTime,wt.workType) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa Inner Join ja.workType wt   Where ja.isActive='true'")
	List<JobAdvertDtoWithQuery> getByIsActive();

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.workTime,wt.workType) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa Inner Join ja.workType wt   Where ja.isActive='true' and ja.employer.id=:employerId")
	List<JobAdvertDtoWithQuery> getByIsActiveAndEmployerId(int employerId);

	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery(ja.id,ja.openPositionCount,ja.createDate,ja.lastApplyDate,e.companyName,jt.title,c.name,el.educationLevel,wa.workTime,wt.workType) "
			+ "From JobAdvert ja Inner Join ja.employer e Inner Join ja.jobTitle jt Inner Join ja.city c Inner Join ja.educationLevel el Inner Join ja.ofWorking wa Inner Join ja.workType wt  Where ja.isActive='true' Order By ja.createDate Desc")
	List<JobAdvertDtoWithQuery> getByLastApplyDate();

}
