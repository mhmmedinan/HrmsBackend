package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery;
import kodlamaio.hrms.entities.dtos.JobAdvertFilter;

public interface JobAdvertService {

	Result add(JobAdvert jobAdvert);

	Result update(JobAdvert jobAdvert);
	
	Result delete(int id);

	Result falseIsActived(int id);

	Result trueIsActived(int id);

	DataResult<List<JobAdvertDtoWithQuery>> getByIsActiveTrueAndEmployerId(int employerId);

	DataResult<List<JobAdvertDtoWithQuery>> getByIsActive();

	DataResult<List<JobAdvertDtoWithQuery>> getByLastApplyDate();
	
	DataResult<List<JobAdvert>> getAll();

	DataResult<JobAdvert> getById(int id);
	
	DataResult<List<JobAdvert>> getAllSorted();
	
	DataResult<List<JobAdvert>> getAllFilterAndPage(int pageNo,int pageSize,JobAdvertFilter advertFilter);
}
