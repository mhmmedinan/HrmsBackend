package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Technologie;

public interface TechnologieDao extends JpaRepository<Technologie, Integer> {

	List<Technologie> getByResumeId(int resumeId);
	
	Technologie getById(int id);
}
