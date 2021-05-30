package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

	List<JobAdvert> getByIsActiveAndEmployerId(boolean isActive,int id);
	List<JobAdvert> getByIsActive(boolean status);
	
	@Query("From JobAdvert where isActive='true'  ORDER BY lastApplyDate DESC ")
	List<JobAdvert> getByLastApplyDate(boolean status);


	
}
