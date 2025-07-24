package com.recruit.controller.sample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.router.Route;
import com.recruit.router.Router;
import com.recruit.router.ViewConstants;
import com.recruit.utils.file.FileExtensionValidator;

@Controller
@RequestMapping("/file")
public class FileController {

	@GetMapping("/test")
	public String sample(Model model) {

		System.out.println("테스트..!!");
		
		Route  route  = Router.getInstance().getRoute("file");
		model.addAttribute("route", route);
		
		System.out.println("ViewConstants.INDEX : " + ViewConstants.INDEX);
		System.out.println("route : " + route.toString());
		
		return ViewConstants.INDEX;
	}	

	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("file 확장자 체크 및 업로드 테스트..!!");
		
		Map<String, Object> response = new HashMap<>();
		
		// 파일명과 확장자 체크
		String filename = file.getOriginalFilename();
		if (filename == null || !FileExtensionValidator.isValidFile(filename)) {
			response.put("status", "error");
			response.put("message", "허용되지 않은 파일 확장자입니다.");
			return ResponseEntity.badRequest().body(response); 
		}
		
		try {
			StringBuilder result = new StringBuilder();
			
			long fileSize = file.getSize();
            result.append("파일 이름: ").append(filename).append("\n")
                  .append("파일 크기: ").append(fileSize).append(" bytes");
			
			response.put("status", "success");
			response.put("content", result.toString());
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			response.put("status", "error");
			response.put("message", e.getMessage());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}		
}
