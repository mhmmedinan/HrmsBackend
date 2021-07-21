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
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CantidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserDao userDao;
	private ActivationService activationService;
	private UserCheckService userCheckService;

	@Autowired
	public CantidateManager(CandidateDao candidateDao, UserDao userDao, ActivationService activationService,
			UserCheckService userCheckService) {
		super();
		this.candidateDao = candidateDao;
		this.userDao = userDao;
		this.activationService = activationService;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(Candidate candidate) {
		var result = BusinessRules.run(checkIfPasswordExists(candidate), checkIfMailExists(candidate),
				checkIfIdentityNumberExists(candidate), validateMernis(candidate));
		if (result != null) {
			return result;
		} else if (activationService.sendMail(candidate)) {
			this.candidateDao.save(candidate);
			return new SuccessResult(Messages.candidateAdded);
		}

		return new ErrorResult(Messages.candidateInsertionError);
	}

	@Override
	public Result delete(Candidate candidate) {
		this.candidateDao.delete(candidate);
		return new SuccessResult(Messages.candidateDeleted);
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), Messages.listAll);

	}
	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getById(id));
	}

	private Result checkIfPasswordExists(Candidate candidate) {
		if (!candidate.getPassword().equals(candidate.getPasswordRepeat())) {
			return new ErrorResult(Messages.checkIfPasswordExists);
		}
		return new SuccessResult();
	}

	private Result checkIfMailExists(Candidate candidate) {
		if (this.userDao.findUserByEmail(candidate.getEmail()) != null) {
			return new ErrorResult(Messages.checkIfMailExists);
		}

		return new SuccessResult();
	}

	private Result checkIfIdentityNumberExists(Candidate candidate) {
		if (this.candidateDao.getByIdentityNumber(candidate.getIdentityNumber()) != null) {
			return new ErrorResult(Messages.checkIfIdentityNumberExists);
		}
		return new SuccessResult();
	}

	private Result validateMernis(Candidate candidate) {
		if (userCheckService.validateByMernis(Long.parseLong(candidate.getIdentityNumber()), candidate.getFirstName(),
				candidate.getLastName(), candidate.getBirthOfYear())) {
			return new SuccessResult(Messages.validateSuccessMernis);
		}
		return new ErrorResult(Messages.validateErrorMernis);
	}

	

}
