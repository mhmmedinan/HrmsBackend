package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kodlamaio.hrms.entities.concretes.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

	@Query("From JobExperience j Where j.resume.id=:resumeId Order by j.endDate Desc")
	List<JobExperience> getByResumeId(int resumeId);
	
	JobExperience getById(int id);
}
