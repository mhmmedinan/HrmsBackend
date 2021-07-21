package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployeeManager implements EmployeeService {
	
	private EmployeeDao  employeeDao;
	private EmployerDao employerDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao,EmployerDao employerDao) {
		super();
		this.employeeDao = employeeDao;
		this.employerDao=employerDao;
	}


	@Override
	public Result add(Employee employee) {
		this.employeeDao.save(employee);
		return new SuccessResult(Messages.employeeAdded);
	}


	@Override
	public Result update(int id, String email, String password, String passwordRepeat, String firstName,
			String lastName) {
		Employee employee = this.employeeDao.getById(id);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setPasswordRepeat(passwordRepeat);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		this.employeeDao.save(employee);
		return new SuccessResult(Messages.employeeUpdated);
	}


	@Override
	public DataResult<List<Employee>> getById(int id) {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findById(id));
	}
	
	@Override
	public Result confirmUpdateEmployer(int employerId) {
		Employer employer = employerDao.getOne(employerId);
		Map<String, Object> jsonUpdate = employer.getHistory();
		employer.setEmail(jsonUpdate.get("email").toString());
		employer.setPassword(jsonUpdate.get("password").toString());
		employer.setPasswordRepeat(jsonUpdate.get("passwordRepeat").toString());
		employer.setCompanyName(jsonUpdate.get("companyName").toString());
		employer.setWebAddress(jsonUpdate.get("webAddress").toString());
		employer.setPhoneNumber(jsonUpdate.get("phoneNumber").toString());
		employer.setHistory(null);
		employer.setActivated(true);
		this.employerDao.save(employer);
		return new SuccessResult("Güncellendi");
	}

}
