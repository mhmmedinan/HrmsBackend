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
import kodlamaio.hrms.business.abstracts.TechnologieService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.Technologie;
import kodlamaio.hrms.entities.dtos.TechnologieDto;

@RestController
@RequestMapping("api/technologies")
@CrossOrigin
public class TechnologiesController {
	private TechnologieService technologieService;

	@Autowired
	public TechnologiesController(TechnologieService technologieService) {
		super();
		this.technologieService = technologieService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody @Valid TechnologieDto technologieDto) {
		return ResponseEntity.ok(this.technologieService.add(technologieDto));
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody TechnologieDto technologieDto) {
		return ResponseEntity.ok(this.technologieService.update(technologieDto));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(int id) {
		return ResponseEntity.ok(this.technologieService.delete(id));
	}

	@GetMapping("/getall")
	public DataResult<List<Technologie>> getAll() {
		return this.technologieService.getAll();
	}
	
	@GetMapping("/getbyresumeid")
	public DataResult<List<Technologie>> getByResumeId(int resumeId) {
		return this.technologieService.getByResumeId(resumeId);
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
