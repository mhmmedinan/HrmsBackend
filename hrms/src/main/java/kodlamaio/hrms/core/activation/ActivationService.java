package kodlamaio.hrms.core.activation;

import kodlamaio.hrms.core.entities.User;

public interface ActivationService {

	public boolean sendMail(User user);

}
