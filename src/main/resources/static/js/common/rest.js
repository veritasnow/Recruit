const restApi = {
  request: function (method, urlLink, data) {
    if (method === 'GET' && data) {
      const queryString = Object.entries(data)
        .map(([key, val]) => `${encodeURIComponent(key)}=${encodeURIComponent(val)}`)
        .join('&');
      urlLink += (urlLink.includes('?') ? '&' : '?') + queryString;
      data = null; // GET은 body에 data 없음
    }

    return $.ajax({
      type: method,
      url: urlLink,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: data ? JSON.stringify(data) : null,
    }).fail(function(jqXHR) {
      // 서버에서 온 에러 메시지를 파싱해 alert 띄움
      let message = jqXHR.responseText || '알 수 없는 에러가 발생했습니다.';
      try {
        const res = JSON.parse(jqXHR.responseText);
        if (res.message) message = res.message;
      } catch (e) {
        // JSON 파싱 실패하면 무시
      }
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
  }
};