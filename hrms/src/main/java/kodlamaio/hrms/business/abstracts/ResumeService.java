package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;

public interface ResumeService {

	Result add(Resume resume);
	
	Result update(String coverLetter,String githubAddress,String linkedinAddress,int resumeId);

	Result delete(Resume resume);

	Result upload(int candidateId, MultipartFile file);
	
	DataResult<List<Resume>> getByCandidateId(int candidateId);

	DataResult<List<Resume>> getAll();
}
