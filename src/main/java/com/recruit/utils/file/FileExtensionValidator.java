package com.recruit.utils.file;

import java.util.Arrays;
import java.util.List;

public class FileExtensionValidator {
	
	// 허용된 확장자 목록
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
    		"jpg", "jpeg", "png", "gif", "bmp", "webp",
    	    "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "csv",
    	    "zip", "rar", "7z",
    	    "mp3", "wav", "mp4", "mov"
    		);
    
    /**
     * 파일 이름으로부터 확장자를 추출하여 유효한지 확인
     * @param filename 업로드된 파일 이름
     * @return true: 유효함, false: 허용되지 않은 확장자
     */
    public static boolean isValidFile(String filename) {
    	if (filename == null || filename.trim().isEmpty()) {
    		return false;
    	}
    	
    	String extension = getFileExtension(filename);
    	return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }
    
    /**
     * 파일 이름에서 확장자를 추출
     * @param filename 파일 이름
     * @return 확장자, 확장자가 없으면 빈 문자열
     */
    public static String getFileExtension(String filename) {
    	int lastDotIndex = filename.lastIndexOf('.');
    	if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
    		return "";
    	}
    	
    	return filename.substring(lastDotIndex + 1); 
    }
}
