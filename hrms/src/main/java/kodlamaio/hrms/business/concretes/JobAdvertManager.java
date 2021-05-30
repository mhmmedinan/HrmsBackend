package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;
	private ModelMapper modelMapper;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao, ModelMapper modelMapper) {
		super();
		this.jobAdvertDao = jobAdvertDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Result add(JobAdvert jobAdvert) {

		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult(Messages.jobAdvertAdded);
	}

	@Override
	public DataResult<List<JobAdvertDto>> getByIsActive() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.dtoAdvert(this.jobAdvertDao.getByIsActive(true)));
	}

	@Override
	public DataResult<List<JobAdvertDto>> getByLastApplyDate() {

		return new SuccessDataResult<List<JobAdvertDto>>(this.dtoAdvert(this.jobAdvertDao.getByLastApplyDate(true)));
	}

	@Override
	public DataResult<List<JobAdvertDto>> findByIsActiveTrueAndEmployerId(int id) {

		return new SuccessDataResult<List<JobAdvertDto>>(
				this.dtoAdvert(this.jobAdvertDao.getByIsActiveAndEmployerId(true, id)));
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
	public DataResult<JobAdvert> getById(int id) {

		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getOne(id));
	}

	@Override
	public Result update(JobAdvert jobAdvert) {
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult(Messages.jobAdvertUpdated);
	}

	private List<JobAdvertDto> dtoAdvert(List<JobAdvert> jobAdvert) {
		List<JobAdvertDto> jobAdvertDtos = new ArrayList<JobAdvertDto>();
		jobAdvert.forEach(item -> {
			JobAdvertDto dto = this.modelMapper.map(item, JobAdvertDto.class);
			dto.setCompanyName(item.getEmployer().getCompanyName());
			jobAdvertDtos.add(dto);
		});

		return jobAdvertDtos;
	}

	

}
