package com.cafe24.shoppingmall.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	// 파일업로드 위치는 밖으로 빼서 저장해야한다 .  /shoppingmall 로시작
	private static final String SAVE_PATH = "/shoppingmall/product";
	// 프로젝트 경로 / images/product 를  경로로 설정 
	// spring-servlet.xml에서 설정하여 images/product/**의 파일을 찾는 요청이 오면 
	// 프로젝트의 가장최상단의 드라이드 현재는 d:/shoppingmall/로 매핑되어 해당 폴더에서 파일을찾음  
	private static final String URL = "/images/product/";
	
	public String restore(MultipartFile multipartFile) {
		
		String url = "";
		String saveFileName;
		try {	
			
			if (multipartFile.isEmpty()) {
				return url;
			}
			// 저장할때는 파일을 불러올때 이름 중복이 될 수 있기 때문에 오리지널 파일이름으로 저장하면안된다.
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1); // 마지막 .의 인덱스를 찾아서 +1 해줌
			saveFileName = generateSaveFileName(extName); // 파일명.확장자
			long fileSize = multipartFile.getSize();
			
			System.out.println("############" + originalFileName);
			System.out.println("############" + extName);
			System.out.println("############" + saveFileName);
			System.out.println("############" + fileSize);
			
			byte[] fileData = multipartFile.getBytes();
			//  프로젝트가 실행된  곳의 최상위경로 d://shoppingmall/product/파일명.확장자
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			

		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return URL+saveFileName;
	}

	private String generateSaveFileName(String extName) {
		// 파일저장할때 년,월,일,시간,분,초,ms 까지 저장해서 db에 넣어두면된다. 거의 중복없음
		// 파일저장시 폴더경로를 나눠서 저장해야함

		String fileName = "";
		Calendar calendar = Calendar.getInstance();

//		s+= "hello" -> new StringBuffer(s).append("hello"); // new 작업이 계속일어나서 상당히 비효율적인 코드

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}

}
