package kodlamaio.hrms.core.activation;

import kodlamaio.hrms.entities.concretes.User;

public interface ActivationService {
	
	public boolean sendMail(User user);

}
