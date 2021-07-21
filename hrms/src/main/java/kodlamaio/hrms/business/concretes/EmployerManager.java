package kodlamaio.hrms.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.activation.ActivationService;
import kodlamaio.hrms.core.activation.EmployeeValidationService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private UserDao userDao;
	private EmployerDao employerDao;
	@Autowired
	private ActivationService activationService;
	@Autowired
	private EmployeeValidationService employeeValidationService;

	@Autowired
	public EmployerManager(UserDao userDao, EmployerDao employerDao, ActivationService activationService,
			EmployeeValidationService employeeValidationService) {
		super();
		this.userDao = userDao;
		this.employerDao = employerDao;
		this.activationService = activationService;
		this.employeeValidationService = employeeValidationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.listEmployerAll);
	}

	@Override
	public Result add(Employer employer) {

		var result = BusinessRules.run(checkIfMailExists(employer), checkIfDomainExists(employer),
				checkIfPasswordExists(employer));
		if (result != null) {
			return result;
		} else if (activationService.sendMail(employer) && employeeValidationService.employeeValid(employer)) {
			employerDao.save(employer);
			return new SuccessResult(Messages.employerAdded);
		}

		return new ErrorResult(Messages.employerInsertionError);

	}
	
	
	@Override
	public Result update(Employer employer) throws JSONException {
		Employer employerUpdate = employerDao.getOne(employer.getId());
		employerUpdate.setHistory(getJson(employer));
		employerUpdate.setActivated(false);
		this.employerDao.save(employerUpdate);
		
		return new SuccessResult("Sisem personeli tarafından onaylandıktan sonra güncellenecektir");
	}
	
	
	@Override
	public DataResult<List<Employer>> getById(int id) {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getById(id));
	}
	
	@Override
	public DataResult<List<Employer>> getByIsAcivatedUpdate() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByIsActivatedUpdate());
	}
	
	@Override
	public Map<String, Object> getJson(Employer employer) {
		Map<String, Object> json = new HashMap<>();
		json.put("email", employer.getEmail());
		json.put("companyName", employer.getCompanyName());
		json.put("webAddress", employer.getWebAddress());
		json.put("password", employer.getPassword());
		json.put("passwordRepeat", employer.getPasswordRepeat());
		json.put("phoneNumber", employer.getPhoneNumber());
		return json;
	}

	private Result checkIfMailExists(Employer employer) {
		if (this.userDao.findUserByEmail(employer.getEmail()) != null) {
			return new ErrorResult(Messages.checkIfMailExists);
		}

		return new SuccessResult();
	}

	private Result checkIfPasswordExists(Employer employer) {
		if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
			return new ErrorResult(Messages.checkIfPasswordExists);
		}
		return new SuccessResult();
	}

	private Result checkIfDomainExists(Employer employer) {
		if (!employer.getEmail().contains("@" + employer.getEmail().substring(employer.getEmail().indexOf("@") + 1))) {
			return new ErrorResult(Messages.checkIfDomainExists);
		}
		return new SuccessResult();
	}


}
