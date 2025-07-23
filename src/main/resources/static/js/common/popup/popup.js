const popupUtil = {
	openPopupGet: (url, params = {}, customOptions = {}) => {
		const defaultOptions = {
			width     : 600,
			height    : 500,
			top       : 100,
			left      : 100,
			scrollbars: 'yes',
			resizable : 'yes'
		};

		const options = Object.assign({}, defaultOptions, customOptions);

		let optionString = '';
		for (let key in options) {
			if (options.hasOwnProperty(key)) {
				optionString += `${key}=${options[key]},`;
			}
		}
		optionString = optionString.slice(0, -1);

		const queryString = new URLSearchParams(params).toString();
		const fullUrl = queryString ? `${url}?${queryString}` : url;

		window.open(fullUrl, '_blank', optionString);
	}
};