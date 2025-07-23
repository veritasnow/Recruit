const validationUtil = {
	// 값이 null, undefined 또는 공백 문자열인지 확인
	isEmpty: function (value) {
		return value == null || String(value).trim() === '';
	},

	// 값이 숫자인지 확인 (비어 있지 않고, 숫자로 변환 가능한 경우)
	isNumber: function (value) {
		return !this.isEmpty(value) && !isNaN(value);
	},

	// 값이 정수인지 확인 (예: 10, -3 등)
	isInteger: function (value) {
		return this.isNumber(value) && Number.isInteger(Number(value));
	},

	// 영문자만 포함되었는지 확인 (대소문자 모두 허용)
	isAlpha: function (value) {
		return /^[A-Za-z]+$/.test(value);
	},

	// 영문자 또는 숫자만 포함되었는지 확인
	isAlphaNumeric: function (value) {
		return /^[A-Za-z0-9]+$/.test(value);
	},

	// 한글만 포함되었는지 확인
	isKorean: function (value) {
		return /^[가-힣]+$/.test(value);
	},

	// 유효한 날짜 형식인지 확인 (Date 객체로 변환 가능한지)
	isValidDate: function (value) {
		if (this.isEmpty(value)) return false;
		const date = new Date(value);
		return !isNaN(date.getTime());
	},

	// 오늘 이전 날짜인지 확인
	isPastDate: function (value) {
		const date = new Date(value);
		const now = new Date();
		return this.isValidDate(value) && date < now;
	},

	// 오늘 이후 날짜인지 확인
	isFutureDate: function (value) {
		const date = new Date(value);
		const now = new Date();
		return this.isValidDate(value) && date > now;
	},

	// 이메일 형식인지 확인 (간단한 정규표현식 사용)
	isEmail: function (value) {
		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
	},

	// 휴대폰 번호 형식인지 확인 (010, 011 등 포함)
	isPhoneNumber: function (value) {
		return /^01[0-9]-?\d{3,4}-?\d{4}$/.test(value);
	},

	// 비밀번호 조건 1: 대문자 + 소문자 + 숫자 + 특수문자 포함, 8자 이상
	isStrongPassword: function (value) {
		return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(value);
	},

	// 비밀번호 조건 2: 문자(대소문자 무관) + 숫자 + 특수문자 포함, 8자 이상
	isMediumPassword: function (value) {
		return /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(value);
	},

	// 비밀번호 조건 3: 문자(대소문자 무관) + 숫자 포함, 6자 이상
	isBasicPassword: function (value) {
		return /^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d]{6,}$/.test(value);
	},

	// 공백(띄어쓰기 등)이 포함되어 있는지 확인
	containsWhitespace: function (value) {
		return /\s/.test(value);
	},

	// 문자열 길이가 지정된 범위(min ~ max) 내에 있는지 확인
	hasLengthBetween: function (value, min, max) {
		if (this.isEmpty(value)) return false;
		const len = value.length;
		return len >= min && len <= max;
	}
};