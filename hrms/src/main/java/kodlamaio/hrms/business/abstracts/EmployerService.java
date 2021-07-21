package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public interface EmployerService {

	DataResult<List<Employer>> getAll();
	
	DataResult<List<Employer>> getById(int id);
	
	DataResult<List<Employer>> getByIsAcivatedUpdate();

	Result add(Employer employer);
	
	Result update(Employer employer) throws JSONException;
	
	Map<String, Object> getJson(Employer employer);
}
