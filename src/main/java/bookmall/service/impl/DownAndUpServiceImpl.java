package bookmall.service.impl;

import bookmall.service.DownAndUpService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class DownAndUpServiceImpl implements DownAndUpService {
	@Override
	public String upFile(MultipartFile upFileName, String roadName) throws IOException {
		//获取上传的文件的文件名
		String fileName = upFileName.getOriginalFilename();
		//处理文件重名问题
		//获取上传文件的后缀名
		String hzName = fileName.substring(fileName.lastIndexOf("."));
		//将UUID和上传文件的后缀名拼接到一起,拼接后的结果作为文件名  .replaceAll()替换所有"-"
		fileName = UUID.randomUUID().toString() + hzName;

		//最终路径
		String finalPath = roadName + File.separator + fileName;

		//将文件转义到地址中
		upFileName.transferTo(new File(finalPath));

		return fileName;
	}

	public ResponseEntity<byte[]> downLoad(String downLoad, String downLoadNameAfter) throws IOException {
		System.out.println();
		//创建输入流
		InputStream is = new FileInputStream(downLoad);
		//创建字节数组
		byte[] bytes = new byte[is.available()];
		//将流读到字节数组中
		is.read(bytes);
		//创建HttpHeaders对象设置响应头信息
		MultiValueMap<String, String> headers = new HttpHeaders();
		//设置要下载方式以及下载文件的名字   处理中文文件乱码    String fileName = new String ("图片".getBytes("UTF-8"),"ISO-8859-1")
		String fileName = new String(downLoadNameAfter.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		headers.add("Content-Disposition", "attachment;filename=" + fileName);
		//设置响应状态码
		HttpStatus statusCode = HttpStatus.OK;
		//创建ResponseEntity对象
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
		//关闭输入流
		is.close();

		return responseEntity;
	}
}
