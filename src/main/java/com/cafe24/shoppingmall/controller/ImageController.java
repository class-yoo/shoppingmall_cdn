package com.cafe24.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.FileUploadService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private FileUploadService fileUploadService;

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONResult uploadFile(@RequestParam(value = "imageFile") MultipartFile multipartFile) {
		String saveFileName = fileUploadService.restore(multipartFile);
		System.out.println("saveFileName=" + saveFileName);

		return JSONResult.success(saveFileName);
	}
	
}
