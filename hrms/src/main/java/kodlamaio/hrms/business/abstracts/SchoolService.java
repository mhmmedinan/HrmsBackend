package kodlamaio.hrms.business.abstracts;


import java.util.List;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.School;
import kodlamaio.hrms.entities.dtos.SchoolDto;

public interface SchoolService {

	Result add(SchoolDto schoolDto);
	
	Result delete(int id);
	
	Result update(SchoolDto schoolDto);

	DataResult<List<School>> getBySchoolResumeId(int resumeId);

	DataResult<List<School>> getAll();
}
