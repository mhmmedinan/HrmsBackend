package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.entities.User;

public interface UserService {

	DataResult<List<User>> getAll();
}
