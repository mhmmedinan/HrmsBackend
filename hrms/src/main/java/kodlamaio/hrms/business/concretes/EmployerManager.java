package kodlamaio.hrms.business.concretes;

import java.util.List;

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
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
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
	public EmployerManager(UserDao userDao,EmployerDao employerDao,ActivationService activationService,EmployeeValidationService employeeValidationService) {
		super();
		this.userDao = userDao;
		this.employerDao=employerDao;
		this.activationService=activationService;
		this.employeeValidationService=employeeValidationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}

	@Override
	public Result add(Employer employer) {
		
		var result = BusinessRules.run(checkIfMailExists(employer),checkIfDomainExists(employer),checkIfPasswordExists(employer),validate(employer));
		if(result!=null) {
			return result;
		}
		else if( activationService.sendMail(employer) && employeeValidationService.employeeValid(employer) ) {
			employerDao.save(employer);
			return new SuccessResult(Messages.employerAdded);
		}

			return new ErrorResult(Messages.employerInsertionError);
		
	}

	
	
	private Result checkIfMailExists(Employer employer) {
		if (this.userDao.findUserByEmail(employer.getEmail()) !=null)
		{
			return new ErrorResult(Messages.checkIfMailExists);
		}
		
		return new SuccessResult();
	}
	
	private Result validate(Employer employer) 
	{
		if(employer.getEmail()==null
				|| employer.getPassword()==null
				|| employer.getPasswordRepeat()==null
				|| employer.getCompanyName()==null
				|| employer.getWebAddress()==null
				|| employer.getPhoneNumber()==null)
		{
			return new ErrorResult(Messages.validateMessage);
		}
		return new SuccessResult();
				
	}
	
	private Result checkIfPasswordExists(Employer employer) {
		if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
			return new ErrorResult(Messages.checkIfPasswordExists);		}
		return new SuccessResult();
	}
	
	private Result checkIfDomainExists(Employer employer) {
		if(!employer.getEmail().contains("@" + employer.getEmail().substring(employer.getEmail().indexOf("@")+1))) {
			return new ErrorResult(Messages.checkIfDomainExists);
		}
		return new SuccessResult();
	}
}
