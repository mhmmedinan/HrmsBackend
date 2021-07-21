package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavoriteJobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.FavoriteJobAdvertDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.entities.concretes.FavoriteJobAdvert;

@Service
public class FavoriteJobAdvertManager implements FavoriteJobAdvertService {

	private FavoriteJobAdvertDao favoriteJobAdvertDao;
	private CandidateDao candidateDao;
	private JobAdvertDao jobAdvertDao;
	@Autowired
	public FavoriteJobAdvertManager(FavoriteJobAdvertDao favoriteJobAdvertDao,CandidateDao candidateDao,JobAdvertDao jobAdvertDao) {
		super();
		this.favoriteJobAdvertDao = favoriteJobAdvertDao;
		this.candidateDao=candidateDao;
		this.jobAdvertDao=jobAdvertDao;
	}

	@Override
	public DataResult<List<FavoriteJobAdvert>> getByCandidateId(int candidateId) {
	return new SuccessDataResult<List<FavoriteJobAdvert>>(this.favoriteJobAdvertDao.getByCandidateId(candidateId));
	}

	@Override
	public Result addFavorite(int candidateId, int jobAdvertId) {
		if(this.favoriteJobAdvertDao.existsByCandidate_IdAndJobAdvert_Id(candidateId, jobAdvertId)) {
			return new ErrorResult("Bu ilan favorilerinizde mevcut");
		}
		FavoriteJobAdvert favoriteJobAdvert = new FavoriteJobAdvert();
		favoriteJobAdvert.setCandidate(this.candidateDao.getById(candidateId));
		favoriteJobAdvert.setJobAdvert(this.jobAdvertDao.getById(jobAdvertId));
		this.favoriteJobAdvertDao.save(favoriteJobAdvert);
		return new SuccessResult("İlan favorilere eklendi");
		
	}

	@Override
	public Result removeFavorite(int favoriteId) {
		this.favoriteJobAdvertDao.deleteById(favoriteId);
		return new SuccessResult("İlan favorilerden kaldırıldı");
	}

}
