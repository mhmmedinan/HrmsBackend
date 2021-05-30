package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

@RestController
@RequestMapping("/api/jobadverts")
public class JobAdvertsController {
		private JobAdvertService advertService;

		@Autowired
		public JobAdvertsController(JobAdvertService advertService) {
			super();
			this.advertService = advertService;
		}
		
		@PostMapping("/add")
		public Result add(@RequestBody JobAdvert jobAdvert) {
			return this.advertService.add(jobAdvert);
		}
		
		
		@GetMapping("getbyisactiveandemployerid")
		public DataResult<List<JobAdvertDto>> getByIsActiveAndEmployerCompany(int id){
			return this.advertService.findByIsActiveTrueAndEmployerId(id);
		}
		
		@GetMapping("getall")
		public DataResult<List<JobAdvertDto>> getByIsActive(){
			return this.advertService.getByIsActive();
		}
		
		@GetMapping("falseisactive")
		public Result falseIsActived(int id){
			return this.advertService.falseIsActived(id);
		}
		
		@GetMapping("trueisactive")
		public Result trueIsActived(int id){
			return this.advertService.trueIsActived(id);
		}
		
		@GetMapping("getbylastapplydate")
		public DataResult<List<JobAdvertDto>> getByLastApplyDate(){
			return this.advertService.getByLastApplyDate();
		}
}
