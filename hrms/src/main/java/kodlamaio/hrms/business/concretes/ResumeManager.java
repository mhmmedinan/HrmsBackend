package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.business.constans.Messages;
import kodlamaio.hrms.core.utilities.cloudinaryHelpers.CloudinaryImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;

@Service
public class ResumeManager implements ResumeService {

	private ResumeDao resumeDao;
	private CloudinaryImageService imageService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, CloudinaryImageService imageService) {
		super();
		this.resumeDao = resumeDao;
		this.imageService = imageService;
	}

	@Override
	public Result add(Resume resume) {
		this.resumeDao.save(resume);
		return new SuccessResult(Messages.resumeAdded);
	}

	@Override
	public DataResult<List<Resume>> getByCandidateId(int candidateId) {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.getByCandidateId(candidateId),
				Messages.listCandidateCv);
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll(), Messages.listAllCv);
	}

	@Override
	public Result upload(int candidateId, MultipartFile file) {
		var result = this.imageService.upload(file);
		var url = result.getData().get("url");

		Resume resume = this.resumeDao.getOne(candidateId);
		resume.setUrl(url.toString());
		this.resumeDao.save(resume);
		return new SuccessResult("eklendi");
	}

	@Override
	public Result delete(Resume resume) {
		this.resumeDao.delete(resume);
		return new SuccessResult(Messages.resumeDeleted);
	}
}
