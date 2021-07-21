package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.School;

public interface SchoolDao extends JpaRepository<School, Integer> {

	@Query("From School s Where s.resume.id=:resumeId Order by s.endDate Desc")
	List<School> getByResumeId(int resumeId);
	
	School getById(int schoolId);

}
