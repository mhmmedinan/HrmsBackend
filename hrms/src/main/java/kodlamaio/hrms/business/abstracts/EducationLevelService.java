package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EducationLevel;

public interface EducationLevelService {

	Result add(EducationLevel educationLevel);
	DataResult<List<EducationLevel>> getAll();
}
