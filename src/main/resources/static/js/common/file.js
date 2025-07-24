const fileUtil = {
	// 허용된 파일 확장자
	allowedExtensions: [
		"jpg", "jpeg", "png", "gif", "bmp", "webp",
		"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "csv",
		"zip", "rar", "7z",
		"mp3", "wav", "mp4", "mov"
	],

	// 파일 확장자 체크
	checkFileExtension: function(fileName) {
		const extension = fileName.split('.').pop().toLowerCase();
		return this.allowedExtensions.includes(extension);
	}
};