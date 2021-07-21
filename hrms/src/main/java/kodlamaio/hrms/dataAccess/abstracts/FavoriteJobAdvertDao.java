package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.FavoriteJobAdvert;

public interface FavoriteJobAdvertDao extends JpaRepository<FavoriteJobAdvert, Integer> {
	@Query("From FavoriteJobAdvert f where f.candidate.id=:id")
	List<FavoriteJobAdvert> getByCandidateId(int id);
    boolean existsByCandidate_IdAndJobAdvert_Id(int candidateId,int JobAdId);

}
