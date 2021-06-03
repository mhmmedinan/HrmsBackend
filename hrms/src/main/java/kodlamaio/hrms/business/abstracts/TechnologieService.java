package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Technologie;

public interface TechnologieService {

	Result add(Technologie technologie);

	DataResult<List<Technologie>> getAll();
}
