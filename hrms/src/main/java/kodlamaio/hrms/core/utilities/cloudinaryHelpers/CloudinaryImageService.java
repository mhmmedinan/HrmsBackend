package kodlamaio.hrms.core.utilities.cloudinaryHelpers;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface CloudinaryImageService {

	DataResult<Map> upload(MultipartFile multipartFile);

	Map delete(String imageId);

	DataResult<File> convert(MultipartFile multipartFile);
}
