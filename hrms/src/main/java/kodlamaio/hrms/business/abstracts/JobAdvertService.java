package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleDto;

public interface JobAdvertService {

	Result add(JobAdvert jobAdvert);

	Result update(JobAdvert jobAdvert);

	Result falseIsActived(int id);

	Result trueIsActived(int id);

	DataResult<List<JobAdvertWithEmployerWithJobTitleDto>> getByIsActiveTrueAndEmployerId(int employerId);

	DataResult<List<JobAdvertWithEmployerWithJobTitleDto>> getByIsActive();

	DataResult<List<JobAdvertWithEmployerWithJobTitleDto>> getByLastApplyDate();

	DataResult<JobAdvert> getById(int id);
}
