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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDtoWithQuery;
import kodlamaio.hrms.entities.dtos.JobAdvertFilter;

@RestController
@RequestMapping("/api/jobadverts")
@CrossOrigin
public class JobAdvertsController {
	private JobAdvertService advertService;

	@Autowired
	public JobAdvertsController(JobAdvertService advertService) {
		super();
		this.advertService = advertService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody @Valid JobAdvert jobAdvert) {
		return ResponseEntity.ok(this.advertService.add(jobAdvert));
	}
	
	@PostMapping("/delete")
	public Result delete(int id) {
		return this.advertService.delete(id);
	}


	@GetMapping("getbyisactiveandemployerid")
	public DataResult<List<JobAdvertDtoWithQuery>> getByIsActiveAndEmployerId(int employerId) {
		return this.advertService.getByIsActiveTrueAndEmployerId(employerId);
	}

	@GetMapping("getbyisactive")
	public DataResult<List<JobAdvertDtoWithQuery>> getByIsActive() {
		return this.advertService.getByIsActive();
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvert>> getAll() {
		return this.advertService.getAll();
	}
	

	@GetMapping("getallsorted")
	public DataResult<List<JobAdvert>> getAllSorted() {
		return this.advertService.getAllSorted();
	}

	@PostMapping("falseisactive")
	public ResponseEntity<?>  falseIsActived(int id) {
		Result result = this.advertService.falseIsActived(id);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
		
	}

	@PostMapping("trueisactive")
	public ResponseEntity<?>  trueIsActived(int id) {
		Result result = this.advertService.trueIsActived(id);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("/getfilterandpage")
	public Result getFilterAndPage(@RequestParam int pageNo,@RequestParam int pageSize,@RequestBody JobAdvertFilter jobAdvertFilter) {
	return this.advertService.getAllFilterAndPage(pageNo, pageSize, jobAdvertFilter);
	}
	
	@GetMapping("getbylastapplydate")
	public DataResult<List<JobAdvertDtoWithQuery>> getByLastApplyDate() {
		return this.advertService.getByLastApplyDate();
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
