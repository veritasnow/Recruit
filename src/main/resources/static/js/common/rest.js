const restApi = {
	// 내부 토큰 보관용
	token: {
		csrfToken: null,
		csrfHeader: null,
		authToken: null
	},

	// 초기 토큰 자동 설정 (Spring Security CSRF + Session JWT 등)
	initToken: function () {
		const metaCsrf = document.querySelector('meta[name="_csrf"]');
		const metaHeader = document.querySelector('meta[name="_csrf_header"]');

		this.token.csrfToken = metaCsrf?.content || null;
		this.token.csrfHeader = metaHeader?.content || null;
		this.token.authToken = sessionStorage.getItem('ACCESS_TOKEN'); // JWT 등 저장된 경우
	},

	// 수동 설정도 가능
	setToken: function ({ csrfToken, csrfHeader, authToken }) {
		if (csrfToken) this.token.csrfToken = csrfToken;
		if (csrfHeader) this.token.csrfHeader = csrfHeader;
		if (authToken) this.token.authToken = authToken;
	},

	request: function (method, urlLink, data) {
		// 최초 요청 시 토큰 자동 세팅
		if (!this.token.csrfToken || !this.token.csrfHeader) {
			this.initToken();
		}

		if (method === 'GET' && data) {
			const queryString = Object.entries(data)
				.map(([key, val]) => `${encodeURIComponent(key)}=${encodeURIComponent(val)}`)
				.join('&');
			urlLink += (urlLink.includes('?') ? '&' : '?') + queryString;
			data = null; // GET은 body 없음
		}

		// 요청 헤더 설정
		const headers = {};
		if (this.token.csrfToken && this.token.csrfHeader) {
			headers[this.token.csrfHeader] = this.token.csrfToken;
		}
		if (this.token.authToken) {
			headers['Authorization'] = `Bearer ${this.token.authToken}`;
		}

		return $.ajax({
			type: method,
			url: urlLink,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			headers: headers,
			data: data ? JSON.stringify(data) : null,
		}).fail(function (jqXHR) {
			let message = jqXHR.responseText || '알 수 없는 에러가 발생했습니다.';
			try {
				const res = JSON.parse(jqXHR.responseText);
				if (res.message) message = res.message;
			} catch (e) {}
			alert('API 요청 실패: ' + message);
		});
	},

	read: function (urlLink, data) {
		return this.request('GET', urlLink, data);
	},

	save: function (urlLink, data) {
		return this.request('POST', urlLink, data);
	},

	update: function (urlLink, data) {
		return this.request('PUT', urlLink, data);
	},

	delete: function (urlLink, data) {
		return this.request('DELETE', urlLink, data);
	},
	
	// 파일 업로드
	fileUpload: function (urlLink, formData) {
		// 최초 요청 시 토큰 자동 세팅
		if (!this.token.csrfToken || !this.token.csrfHeader) {
			this.initToken();
		}

		// 요청 헤더 설정
		const headers = {};
		if (this.token.csrfToken && this.token.csrfHeader) {
			headers[this.token.csrfHeader] = this.token.csrfToken;
		}
		if (this.token.authToken) {
			headers['Authorization'] = `Bearer ${this.token.authToken}`;
		}

		return $.ajax({
			type: "POST",
			url: urlLink,
			dataType: 'json',
			contentType: false,
			processData: false,
			headers: headers,
			data: formData,
		}).fail(function (jqXHR) {
			let message = jqXHR.responseText || '알 수 없는 에러가 발생했습니다.';
			try {
				const res = JSON.parse(jqXHR.responseText);
				if (res.message) message = res.message;
			} catch (e) {}
			alert('파일 업로드 실패: ' + message);
		});
	}
};

// 페이지 최초 실행 시 자동 토큰 세팅
restApi.initToken();