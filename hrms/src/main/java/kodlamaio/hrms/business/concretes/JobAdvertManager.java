package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
	}

	@Override
	public Result add(JobAdvert jobAdvert) {

		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult(Messages.jobAdvertAdded);
	}

	@Override
	public Result update(JobAdvert jobAdvert) {
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult(Messages.jobAdvertUpdated);
	}

	@Override
	public Result falseIsActived(int id) {
		if (getById(id) == null) {
			return new ErrorResult(Messages.noSuchJobAdvert);
		} else if (getById(id).getData().isActive() == false) {
			return new ErrorResult(Messages.closeJobAdvert);
		}

		JobAdvert jobAdvert = getById(id).getData();
		jobAdvert.setActive(false);
		update(jobAdvert);
		return new SuccessResult(Messages.successCloseJobAdvert);

	}

	@Override
	public Result trueIsActived(int id) {
		if (getById(id) == null) {
			return new ErrorResult(Messages.noSuchJobAdvert);
		} else if (getById(id).getData().isActive() == true) {
			return new ErrorResult(Messages.openJobAdvert);
		}

		JobAdvert jobAdvert = getById(id).getData();
		jobAdvert.setActive(true);
		update(jobAdvert);
		return new SuccessResult(Messages.successOpenJobAdvert);

	}

	@Override
	public DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByIsActiveTrueAndEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>>(
				this.jobAdvertDao.getByIsActiveAndEmployerId(employerId), Messages.listEmployerTrueAll);
	}

	@Override
	public DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByIsActive() {

		return new SuccessDataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>>(this.jobAdvertDao.getByIsActive(),
				Messages.listTrueJobAdvertAll);
	}

	@Override
	public DataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>> getByLastApplyDate() {
		return new SuccessDataResult<List<JobAdvertWithEmployerWithJobTitleWithCityWithEducationLevelWithWayOfWorkingDto>>(this.jobAdvertDao.getByLastApplyDate(),
				Messages.listLastApply);
	}

	@Override
	public DataResult<JobAdvert> getById(int id) {

		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getOne(id));
	}

}
