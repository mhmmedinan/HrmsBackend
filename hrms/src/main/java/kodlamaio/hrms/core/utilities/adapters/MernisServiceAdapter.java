package kodlamaio.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.mernisService.FakeMernisService;

@Service
public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean validateByMernis(long nationalId, String firstName, String lastName, int yearOfBirth) {
		FakeMernisService client = new FakeMernisService();

		try {
			boolean result = client.validateUser(nationalId, firstName, lastName, yearOfBirth);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
