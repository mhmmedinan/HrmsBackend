package kodlamaio.hrms.core.activation;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.entities.User;

@Service
public class EmployeeValidationManager implements EmployeeValidationService {

	@Override
	public boolean employeeValid(User user) {

		return true;
	}

}
