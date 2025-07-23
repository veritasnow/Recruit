const formatUtil = {
	// 숫자에 천 단위 콤마 찍기 (예: 1234567 -> "1,234,567")
	formatMoney: function(value) {
		if (value == null) return '';
		const num = Number(String(value).replace(/,/g, '')); // 콤마 제거 후 숫자 변환
		if (isNaN(num)) return '';
		return num.toLocaleString('en-US'); 
	},

	// 핸드폰 번호 하이픈 자동 삽입 (01012345678 -> 010-1234-5678)
	formatPhoneNumber: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, ''); // 숫자만 추출
		if (digits.length === 10) { // 3-3-4 형태 (예: 0111234567)
			return digits.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
		} else if (digits.length === 11) { // 3-4-4 형태 (예: 01012345678)
			return digits.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
		} else {
			return value; // 길이가 맞지 않으면 원본 반환
		}
	},

	// 이메일 소문자로 변환
	formatEmail: function(value) {
		if (!value) return '';
		return value.trim().toLowerCase();
	},

	// 이름 앞뒤 공백 제거 + 중간 공백은 한 칸만 남기기
	formatName: function(value) {
		if (!value) return '';
		return value.trim().replace(/\s+/g, ' ');
	},

	// 모든 공백 제거
	removeAllWhitespace: function(value) {
		if (!value) return '';
		return value.replace(/\s+/g, '');
	},

	// 우편번호 포맷팅: 5자리 또는 5+4자리 하이픈 삽입 (ex: 123456789 -> 12345-6789)
	formatZipCode: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, '');
		if (digits.length === 5) {
			return digits;
		} else if (digits.length === 9) {
			return digits.replace(/(\d{5})(\d{4})/, '$1-$2');
		} else {
			return value;
		}
	},

	// 카드번호 4자리마다 하이픈 삽입 (ex: 1234123412341234 -> 1234-1234-1234-1234)
	formatCardNumber: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, '');
		return digits.replace(/(.{4})/g, '$1-').slice(0, -1);
	},

	// 날짜를 yyyy-mm-dd 형식으로 포맷팅 (Date 객체 또는 날짜 문자열 입력 가능)
	formatDateYYYYMMDD: function(value) {
		if (!value) return '';
		const date = new Date(value);
		if (isNaN(date.getTime())) return '';
		const yyyy = date.getFullYear();
		const mm = ('0' + (date.getMonth() + 1)).slice(-2);
		const dd = ('0' + date.getDate()).slice(-2);
		return `${yyyy}-${mm}-${dd}`;
	},

	// 국제 전화번호 포맷팅 (ex: 821012345678 -> +82 10 1234 5678)
	formatIntlPhoneNumber: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, '');
		if (digits.startsWith('82') && digits.length === 11) {
			return `+82 ${digits.substr(2,2)} ${digits.substr(4,4)} ${digits.substr(8,4)}`;
		} else if (digits.length === 11 && digits.startsWith('0')) {
			return `+82 ${digits.substr(1,2)} ${digits.substr(3,4)} ${digits.substr(7,4)}`;
		}
		return value;
	},

	// 집 전화번호 하이픈 자동 삽입 (ex: 0212345678 -> 02-1234-5678)
	formatLandlinePhoneNumber: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, '');
		if (digits.startsWith('02') && digits.length === 9) {
			// 02-123-4567
			return digits.replace(/(02)(\d{3})(\d{4})/, '$1-$2-$3');
		} else if (digits.startsWith('02') && digits.length === 10) {
			// 02-1234-5678
			return digits.replace(/(02)(\d{4})(\d{4})/, '$1-$2-$3');
		} else if (digits.length === 10) {
			// 031-123-4567 (3자리 지역번호)
			return digits.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
		} else if (digits.length === 11) {
			// 031-1234-5678
			return digits.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
		} else {
			return value;
		}
	},

	// 자격증 번호 하이픈 자동 삽입 (ex: 123456789 -> 123-456-789)
	formatCertificateNumber: function(value) {
		if (!value) return '';
		const digits = value.replace(/\D/g, '');
		return digits.replace(/(\d{3})(\d{3})(\d{3})/, '$1-$2-$3');
	},

	// 주소 공백 정리 (앞뒤 공백 제거 + 중간 공백 1칸 유지)
	formatAddress: function(value) {
		if (!value) return '';
		return value.trim().replace(/\s+/g, ' ');
	},

	// 경력 기간 포맷팅 (ex: 201801 ~ 202012 -> 2018-01 ~ 2020-12)
	formatCareerPeriod: function(value) {
		if (!value) return '';
		// 공백 제거 후 ~ 기준으로 분리
		const parts = value.replace(/\s+/g, '').split('~');
		if (parts.length !== 2) return value;
		const formatPart = (part) => {
			if (part.length === 6) { // YYYYMM
				return part.slice(0,4) + '-' + part.slice(4,6);
			}
			return part;
		};
		return `${formatPart(parts[0])} ~ ${formatPart(parts[1])}`;
	},

	// 학력 기간 포맷팅 (ex: 2015.03 - 2019.02 -> 2015-03 - 2019-02)
	formatEducationPeriod: function(value) {
		if (!value) return '';
		const parts = value.replace(/\s+/g, '').split('-');
		if (parts.length !== 2) return value;
		const formatPart = (part) => {
			return part.replace(/[./]/g, '-');
		};
		return `${formatPart(parts[0])} - ${formatPart(parts[1])}`;
	},
	
	// before가 after보다 크면 안되는지 확인 (날짜, 숫자 모두 가능)
	isBeforeOrEqual: function(before, after) {
		if (this.isEmpty(before) || this.isEmpty(after)) return false;

		// 날짜 비교 시도
		const beforeDate = new Date(before);
		const afterDate  = new Date(after);
		if (!isNaN(beforeDate.getTime()) && !isNaN(afterDate.getTime())) {
			return beforeDate.getTime() <= afterDate.getTime();
		}

		// 숫자 비교 시도 (숫자로 변환 가능하면)
		const beforeNum = Number(String(before).replace(/,/g, ''));
		const afterNum  = Number(String(after).replace(/,/g, ''));
		if (!isNaN(beforeNum) && !isNaN(afterNum)) {
			return beforeNum <= afterNum;
		}

		// 그 외 문자열 사전순 비교 (필요 시 변경 가능)
		return String(before) <= String(after);
	},	
};