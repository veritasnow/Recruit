const main = {
	menuList: null,

	init() {
    	this.menuList = document.querySelector('#sidebar ul');
	    this.loadMenu();
	},

	async loadMenu() {
	    try {
	      const data = await restApi.read('/menu/list', { id: 1 });
	      this.renderMenu(data);
		} catch (error) {
		  console.error('API 요청 실패:', error);
	    }
	},

	renderMenu(data) {
	    this.menuList.innerHTML = data
	      .map(menu => `<li data-url="${menu.url}" data-title="${menu.name}">${menu.name}</li>`)
	      .join('');
	}
};

document.addEventListener('DOMContentLoaded', () => {
	main.init();
});