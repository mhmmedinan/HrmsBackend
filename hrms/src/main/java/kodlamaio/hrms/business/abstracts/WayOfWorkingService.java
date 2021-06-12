package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WayOfWorking;

public interface WayOfWorkingService {

	Result add(WayOfWorking wayOfWorking);
	DataResult<List<WayOfWorking>> getAll();
 }
