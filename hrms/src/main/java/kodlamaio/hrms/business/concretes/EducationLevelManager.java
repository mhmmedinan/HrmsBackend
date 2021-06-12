package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationLevelService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EducationLevelDao;
import kodlamaio.hrms.entities.concretes.EducationLevel;

@Service
public class EducationLevelManager implements EducationLevelService {

	private EducationLevelDao educationLevelDao;
	@Autowired
	public EducationLevelManager(EducationLevelDao educationLevelDao) {
		super();
		this.educationLevelDao = educationLevelDao;
	}

	@Override
	public Result add(EducationLevel educationLevel) {
		this.educationLevelDao.save(educationLevel);
		return new SuccessResult(Messages.levelAdded);
	}

	@Override
	public DataResult<List<EducationLevel>> getAll() {
		return new SuccessDataResult<List<EducationLevel>>(this.educationLevelDao.findAll(),Messages.levelListed);
	}

}
