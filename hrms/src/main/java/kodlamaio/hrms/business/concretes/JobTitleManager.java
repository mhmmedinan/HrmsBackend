package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(), Messages.listAllTitle);
	}

	@Override
	public Result add(JobTitle jobTitle) {
		var result = BusinessRules.run(checkIfTitleExists(jobTitle));
		if (result != null) {
			return result;
		}
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult(Messages.jobTitleAdded);
	}

	private Result checkIfTitleExists(JobTitle jobTitle) {
		if (this.jobTitleDao.getByTitle(jobTitle.getTitle()) != null) {
			return new ErrorResult(Messages.checkIfTitleExists);
		}
		return new SuccessResult();
	}

}
