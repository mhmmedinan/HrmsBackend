package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery;
import kodlamaio.hrms.entities.dtos.JobAdvertFilter;

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
	
	@Query("Select j  from JobAdvert j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
			+ "and ((:#{#filter.jobTitleId}) IS NULL OR j.jobTitle.jobTitleId IN (:#{#filter.jobTitleId}))"
			+" and ((:#{#filter.workTimeId}) IS NULL OR j.ofWorking.id IN (:#{#filter.workTimeId}))"
			+" and ((:#{#filter.workTypeId}) IS NULL OR j.workType.id IN (:#{#filter.workTypeId}))"
			+ "and j.isActive = true Order By j.createDate Desc ")
	public Page<JobAdvert> getByFilter(@Param("filter") JobAdvertFilter jobAdvertFilter,Pageable pageable);
	
	public JobAdvert getById(int id);

}
