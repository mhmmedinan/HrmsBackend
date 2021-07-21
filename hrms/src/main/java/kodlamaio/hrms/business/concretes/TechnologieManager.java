package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TechnologieService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.dataAccess.abstracts.TechnologieDao;
import kodlamaio.hrms.entities.concretes.Technologie;
import kodlamaio.hrms.entities.dtos.TechnologieDto;

@Service
public class TechnologieManager implements TechnologieService {

	private TechnologieDao technologieDao;
	private ResumeDao resumeDao;

	@Autowired
	public TechnologieManager(TechnologieDao technologieDao,ResumeDao resumeDao) {
		super();
		this.technologieDao = technologieDao;
		this.resumeDao=resumeDao;
	}

	@Override
	public Result add(TechnologieDto technologieDto) {
		
		Technologie technologie = new Technologie();
		technologie.setResume(resumeDao.getById(technologieDto.getResumeId()));
		technologie.setName(technologieDto.getName());
		
		this.technologieDao.save(technologie);
		return new SuccessResult(Messages.techAdded);
	}

	@Override
	public DataResult<List<Technologie>> getAll() {
		return new SuccessDataResult<List<Technologie>>(this.technologieDao.findAll(), Messages.listtech);
	}

	@Override
	public DataResult<List<Technologie>> getByResumeId(int resumeId) {
		return new SuccessDataResult<List<Technologie>>(this.technologieDao.getByResumeId(resumeId));
	}

	@Override
	public Result delete(int id) {
		this.technologieDao.deleteById(id);
		return new SuccessResult(Messages.techDeleted);
	}

	@Override
	public Result update(TechnologieDto technologieDto) {
		Technologie technologie = this.technologieDao.getById(technologieDto.getId());
		technologie.setResume(resumeDao.getById(technologieDto.getResumeId()));
		technologie.setName(technologieDto.getName());
		this.technologieDao.save(technologie);
		return new SuccessResult();
	}

}
