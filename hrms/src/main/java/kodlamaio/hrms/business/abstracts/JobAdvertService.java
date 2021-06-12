package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto;

public interface JobAdvertService {

	Result add(JobAdvert jobAdvert);

	Result update(JobAdvert jobAdvert);

	Result falseIsActived(int id);

	Result trueIsActived(int id);

	DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByIsActiveTrueAndEmployerId(int employerId);

	DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByIsActive();

	DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByLastApplyDate();

	DataResult<JobAdvert> getById(int id);
}
