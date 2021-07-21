package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.dataAccess.abstracts.SchoolDao;
import kodlamaio.hrms.entities.concretes.School;
import kodlamaio.hrms.entities.dtos.SchoolDto;

@Service
public class SchoolManager implements SchoolService {

	private SchoolDao schoolDao;
	private ResumeDao resumeDao;

	@Autowired
	public SchoolManager(SchoolDao schoolDao,ResumeDao resumeDao) {
		super();
		this.schoolDao = schoolDao;
		this.resumeDao=resumeDao;
	}

	@Override
	public Result add(SchoolDto schoolDto) {

		School school = new School();
		school.setResume(this.resumeDao.getById(schoolDto.getResumeId()));
		school.setSchoolName(schoolDto.getSchoolName());
		school.setSchoolEpisode(schoolDto.getSchoolEpisode());
		school.setStartDate(schoolDto.getStartDate());
		school.setEndDate(schoolDto.getEndDate());
		this.schoolDao.save(school);
		return new SuccessResult(Messages.schoolAdded);
	}

	@Override
	public DataResult<List<School>> getBySchoolResumeId(int resumeId) {
		return new SuccessDataResult<List<School>>(this.schoolDao.getByResumeId(resumeId),
				Messages.listCandidateSchool);
	}

	@Override
	public DataResult<List<School>> getAll() {
		return new SuccessDataResult<List<School>>(this.schoolDao.findAll(), Messages.listSchool);
	}

	@Override
	public Result delete(int id) {
		this.schoolDao.deleteById(id);
		return new SuccessResult(Messages.schoolDeleted);
	}

	@Override
	public Result update(SchoolDto schoolDto) {
		
			School school = this.schoolDao.getById(schoolDto.getSchoolId());
			school.setResume(this.resumeDao.getById(schoolDto.getResumeId()));
			school.setSchoolName(schoolDto.getSchoolName());
			school.setSchoolEpisode(schoolDto.getSchoolEpisode());
			school.setStartDate(schoolDto.getStartDate());
			school.setEndDate(schoolDto.getEndDate());
			this.schoolDao.save(school);
			return new SuccessResult(Messages.schoolUpdated);
	}

}
