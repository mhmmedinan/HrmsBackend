package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.JobExperienceDto;

@Service
public class JobExperienceManager implements JobExperienceService {

	private JobExperienceDao jobExperienceDao;
	private ResumeDao resumeDao;

	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao,ResumeDao resumeDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.resumeDao=resumeDao;
	}

	@Override
	public Result add(JobExperienceDto jobExperienceDto) {
		
		JobExperience jobExperience = new JobExperience();
		jobExperience.setResume(resumeDao.getById(jobExperienceDto.getResumeId()));
		jobExperience.setBusinessName(jobExperienceDto.getBusinessName());
		jobExperience.setPosition(jobExperienceDto.getPosition());
		jobExperience.setStartDate(jobExperienceDto.getStartDate());
		jobExperience.setEndDate(jobExperienceDto.getEndDate());
		this.jobExperienceDao.save(jobExperience);
		return new SuccessResult(Messages.jobExperienceAdded);
	}

	@Override
	public DataResult<List<JobExperience>> getByResumeId(int resumeId) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getByResumeId(resumeId),
				Messages.listCandidate);
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(), Messages.listAllExperience);
	}

	@Override
	public Result delete(int id) {
	this.jobExperienceDao.deleteById(id);
	return new SuccessResult(Messages.jobExperienceDeleted);
	}

	@Override
	public Result update(JobExperienceDto jobExperienceDto) {
		JobExperience jobExperience = this.jobExperienceDao.getById(jobExperienceDto.getId());
		jobExperience.setResume(this.resumeDao.getById(jobExperienceDto.getResumeId()));
		jobExperience.setBusinessName(jobExperienceDto.getBusinessName());
		jobExperience.setPosition(jobExperienceDto.getPosition());
		jobExperience.setStartDate(jobExperienceDto.getStartDate());
		jobExperience.setEndDate(jobExperienceDto.getEndDate());
		
		this.jobExperienceDao.save(jobExperience);
		return new SuccessResult();
	}

}
