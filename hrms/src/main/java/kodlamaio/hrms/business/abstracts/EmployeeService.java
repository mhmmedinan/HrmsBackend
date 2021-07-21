package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;

public interface EmployeeService {

	Result add(Employee employee);
	
	Result update(int id,String email,String password,String passwordRepeat,String firstName,String lastName);
	
	Result confirmUpdateEmployer(int employerId);
	
	DataResult<List<Employee>> getById(int id);
}
