package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertService {

	Result add(JobAdvert jobAdvert);
	Result update(JobAdvert jobAdvert);
	Result falseIsActived(int id);
	Result trueIsActived(int id);
	DataResult<List<JobAdvertDto>> findByIsActiveTrueAndEmployerId(int id);
	DataResult<List<JobAdvertDto>> getByIsActive();
	DataResult<List<JobAdvertDto>> getByLastApplyDate();
	DataResult<JobAdvert> getById(int id);	
}
