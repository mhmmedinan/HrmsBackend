package kodlamaio.hrms.business.abstracts;

import java.util.List;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.dtos.LanguageDto;

public interface LanguageService {

	Result add(LanguageDto languageDto);
	
	Result update(LanguageDto languageDto);
	
	Result delete (int id);

	DataResult<List<Language>> getAll();
	
	DataResult<List<Language>> getByResumeId(int resumeId);
}
