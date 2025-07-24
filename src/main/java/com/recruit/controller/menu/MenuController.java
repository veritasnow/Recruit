package com.recruit.controller.menu;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruit.dto.menu.MenuListRequestDto;
import com.recruit.dto.menu.MenuListResponseDto;


@Controller
@RequestMapping("/menu")
public class MenuController {


	// REST용 JSON 반환 메서드 추가
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<?> restTest(@Valid MenuListRequestDto menuListRequestDto, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        // 간단하게 첫 에러 메시지 리턴
	        String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    }

	    System.out.println("REST 메뉴 테스트..!!");
	    System.out.println(menuListRequestDto.toString());
	    
	    // 1. 리스트 생성
	    List<MenuListResponseDto> result = new ArrayList<>();

	    // 2. 항목 추가
	    result.add(new MenuListResponseDto("validSample", "유효성검증 샘플", "validation/sample"));
	    result.add(new MenuListResponseDto("sample2", "샘플2", "sample/page2"));
	    result.add(new MenuListResponseDto("sample3", "샘플3", "sample/page3"));

	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
		
	
}