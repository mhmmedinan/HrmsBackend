package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.WorkTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkTypeDao;
import kodlamaio.hrms.entities.concretes.WorkType;

@Service
public class WorkTypeManager implements WorkTypeService {

	private WorkTypeDao workTypeDao;
	@Autowired
	public WorkTypeManager(WorkTypeDao workTypeDao) {
		super();
		this.workTypeDao = workTypeDao;
	}

	@Override
	public Result add(WorkType workType) {
		this.workTypeDao.save(workType);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<WorkType>> getAll() {
		return new SuccessDataResult<List<WorkType>>(this.workTypeDao.findAll());
	}

}
