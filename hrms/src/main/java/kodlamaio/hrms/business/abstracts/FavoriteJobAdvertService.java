package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.FavoriteJobAdvert;

public interface FavoriteJobAdvertService {
	
	public DataResult<List<FavoriteJobAdvert>> getByCandidateId(int candidateId);
	public Result addFavorite(int candidateId,int jobAdvertId);
	public Result removeFavorite(int favoriteId);

}
