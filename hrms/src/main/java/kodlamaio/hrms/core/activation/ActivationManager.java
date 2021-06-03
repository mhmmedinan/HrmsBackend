package kodlamaio.hrms.core.activation;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.entities.User;

@Service
public class ActivationManager implements ActivationService {

	@Override
	public boolean sendMail(User user) {
		return true;
	}

}
