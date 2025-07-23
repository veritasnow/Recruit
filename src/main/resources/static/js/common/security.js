const securityUtil = {
	// HTML 태그 이스케이프 (XSS 방지)
	escapeHtml: function(str) {
		if (!str) return '';
		return str
			.replace(/&/g, '&amp;')
			.replace(/</g, '&lt;')
			.replace(/>/g, '&gt;')
			.replace(/"/g, '&quot;')
			.replace(/'/g, '&#039;');
	},

	// DOM 삽입 시 안전하게 처리
	safeTextInsert: function(element, text) {
		if (element) element.textContent = text;
	},

	// 입력값에 스크립트 또는 위험 요소가 포함됐는지 검사
	isSafeInput: function(value) {
		if (!value) return true;
		return !(/<script|on\w+=|javascript:|data:/i).test(value.toLowerCase());
	},

	// 위험 요소 제거 (스크립트 태그 등)
	sanitizeInput: function(value) {
		if (!value) return '';
		return value.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi, '')
					.replace(/on\w+="[^"]*"/gi, '')
					.replace(/javascript:/gi, '');
	},

	// 콘솔 로그 차단 (운영용)
	blockConsole: function() {
		console.log = function() {};
		console.debug = function() {};
		console.warn = function() {};
	}
};