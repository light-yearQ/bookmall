package bookmall.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DownAndUpService {
	String upFile(MultipartFile upFileName, String roadName) throws IOException;

	ResponseEntity<byte[]> downLoad(String downLoad, String downLoadNameAfter) throws IOException;
}
