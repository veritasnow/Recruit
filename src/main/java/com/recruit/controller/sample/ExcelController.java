package com.recruit.controller.sample;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

@Controller
@RequestMapping("/excel")
public class ExcelController {

	@GetMapping("/test")
	public String sample(Model model) {

		System.out.println("테스트..!!");
		
		Route  route  = Router.getInstance().getRoute("excel");
		model.addAttribute("route", route);
		
		System.out.println("ViewConstants.INDEX : " + ViewConstants.INDEX);
		System.out.println("route : " + route.toString());
		
		return ViewConstants.INDEX;
	}	

	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("excel 업로드 테스트..!!");
		
		Map<String, Object> response = new HashMap<>();
		
		try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
			Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
			
			StringBuilder result = new StringBuilder();
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
						case STRING: 
							result.append(cell.getStringCellValue() + " | ");
							break;
						case NUMERIC:
							result.append(cell.getNumericCellValue() + " | ");
							break;
						case BOOLEAN:
							result.append(cell.getBooleanCellValue() + " | ");
			                break;
			            case FORMULA:
			            	result.append(cell.getCellFormula() + " | ");
			                break;
			            case BLANK:
			            	result.append("BLANK | ");
			                break;
			            default:
			            	result.append("UNKNOWN | ");
			                break;
					}
				}
				result.append("\n");
			}
			
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
