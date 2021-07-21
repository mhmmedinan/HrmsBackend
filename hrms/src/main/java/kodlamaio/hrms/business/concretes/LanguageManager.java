package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.LanguageDto;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	private ResumeDao resumeDao;

	@Autowired
	public LanguageManager(LanguageDao languageDao,ResumeDao resumeDao) {
		super();
		this.languageDao = languageDao;
		this.resumeDao=resumeDao;
	}

	@Override
	public Result add(LanguageDto languageDto) {
		
		Language language = new Language();
		language.setResume(resumeDao.getById(languageDto.getResumeId()));
		language.setName(languageDto.getName());
		language.setLevel(languageDto.getLevel());
		this.languageDao.save(language);
		return new SuccessResult(Messages.languageAdded);
	}

	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>(this.languageDao.findAll(), Messages.listAllLang);
	}

	@Override
	public DataResult<List<Language>> getByResumeId(int resumeId) {
		return new SuccessDataResult<List<Language>>(this.languageDao.getByResumeId(resumeId));
	}

	@Override
	public Result delete(int id) {
	this.languageDao.deleteById(id);
	return new SuccessResult(Messages.languageDeleted);
	}

	@Override
	public Result update(LanguageDto languageDto) {
		Language language = this.languageDao.getById(languageDto.getId());
		language.setResume(resumeDao.getById(languageDto.getResumeId()));
		language.setName(languageDto.getName());
		language.setLevel(languageDto.getLevel());
		this.languageDao.save(language);
		return new SuccessResult();
		
	}

}
