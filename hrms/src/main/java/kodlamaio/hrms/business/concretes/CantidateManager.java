package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.activation.ActivationService;
import kodlamaio.hrms.core.utilities.adapters.UserCheckService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;


@Service
public class CantidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserDao userDao;
	private ActivationService activationService;
	private UserCheckService userCheckService;
	
	@Autowired
	public CantidateManager(CandidateDao candidateDao,UserDao userDao,ActivationService activationService,
			UserCheckService userCheckService) {
		super();
		this.candidateDao = candidateDao;
		this.userDao=userDao;
		this.activationService=activationService;
		this.userCheckService=userCheckService;
	}

	@Override
	public Result add(Candidate candidate) {
		var result = BusinessRules.run(validate(candidate),checkIfPasswordExists(candidate),
				checkIfMailExists(candidate),checkIfIdentityNumberExists(candidate),validateMernis());
		if(result!=null) {
			return result;
		}
		else if (activationService.sendMail(candidate) ) {
			this.candidateDao.save(candidate);
			return new SuccessResult(Messages.candidateAdded);
		}
		
		return new ErrorResult(Messages.candidateInsertionError);
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll());
		
	}

	
	private Result validate(Candidate candidate) 
	{
		if(candidate.getEmail()==null
				|| candidate.getPassword()==null
				|| candidate.getPasswordRepeat()==null
				|| candidate.getFirstName()==null
				|| candidate.getLastName()==null
				|| candidate.getIdentityNumber()==null
				|| candidate.getBirthOfYear()==null)
		{
			return new ErrorResult(Messages.validateMessage);
			
		}
		return new SuccessResult();
				
	}
	
	private Result checkIfPasswordExists(Candidate candidate) {
		if (!candidate.getPassword().equals(candidate.getPasswordRepeat())) {
			return new ErrorResult(Messages.checkIfPasswordExists);		}
		return new SuccessResult();
	}
	
	private Result checkIfMailExists(Candidate candidate) {
		if (this.userDao.findUserByEmail(candidate.getEmail()) !=null)
		{
			return new ErrorResult(Messages.checkIfMailExists);
		}
		
		return new SuccessResult();
	}
	
	private Result checkIfIdentityNumberExists(Candidate candidate) {
		if (candidateDao.findByIdentityNumber(candidate.getIdentityNumber())!=null) {
			return new ErrorResult(Messages.checkIfIdentityNumberExists);
		}
		return new SuccessResult();
	}
	
	private Result validateMernis() {
		if (userCheckService.validateByMernis(0, null, null, 0)) {
			return new SuccessResult(Messages.validateSuccessMernis);
		}
		return new ErrorResult(Messages.validateErrorMernis);
	}
}
