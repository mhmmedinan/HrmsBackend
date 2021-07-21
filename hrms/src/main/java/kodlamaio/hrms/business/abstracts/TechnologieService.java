package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Technologie;
import kodlamaio.hrms.entities.dtos.TechnologieDto;

public interface TechnologieService {

	Result add(TechnologieDto technologieDto);
	
	Result delete (int id);
	
	Result update(TechnologieDto technologieDto);

	DataResult<List<Technologie>> getAll();
	
	DataResult<List<Technologie>> getByResumeId(int resumeId);
}
