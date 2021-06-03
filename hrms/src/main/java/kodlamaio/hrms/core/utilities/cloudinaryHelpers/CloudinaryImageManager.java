package kodlamaio.hrms.core.utilities.cloudinaryHelpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sun.el.stream.Stream;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryImageManager implements CloudinaryImageService {

	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "djtrju3gt", "api_key", "164212679189242",
			"api_secret", "ACi47Raj4cu2IZCZMThh62lY_NQ"));

	@Override
	public DataResult<Map> upload(MultipartFile multipartFile) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) cloudinary.uploader().upload(multipartFile.getBytes(),
					ObjectUtils.emptyMap());
			return new SuccessDataResult<Map>(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ErrorDataResult<Map>();
	}

	@Override
	public Map delete(String imageId) {
		try {
			Map result = cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DataResult<File> convert(MultipartFile multipartFile) {
		try {
			File file = new File(multipartFile.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
			return new SuccessDataResult<File>(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ErrorDataResult<File>();
	}

}
