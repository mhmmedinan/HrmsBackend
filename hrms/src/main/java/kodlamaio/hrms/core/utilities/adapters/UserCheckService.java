package kodlamaio.hrms.core.utilities.adapters;

public interface UserCheckService {
	boolean validateByMernis(long nationalId, String firstName, String lastName, int yearOfBirth);
}
