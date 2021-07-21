package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.JobExperienceDto;

@RestController
@RequestMapping("api/jobexperiences")
@CrossOrigin
public class JobExperiencesController {

	private JobExperienceService experienceService;

	@Autowired
	public JobExperiencesController(JobExperienceService experienceService) {
		super();
		this.experienceService = experienceService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody @Valid JobExperienceDto jobExperienceDto) {
		return ResponseEntity.ok(this.experienceService.add(jobExperienceDto));
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody JobExperienceDto jobExperienceDto) {
		return ResponseEntity.ok(this.experienceService.update(jobExperienceDto));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		return ResponseEntity.ok(this.experienceService.delete(id));
	}

	@GetMapping("/getbyresumeId")
	public DataResult<List<JobExperience>> getByExperienceResumeId(int resumeId) {
		return this.experienceService.getByResumeId(resumeId);
	}

	@GetMapping("/getall")
	public DataResult<List<JobExperience>> getAll() {
		return this.experienceService.getAll();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;

	}
}
