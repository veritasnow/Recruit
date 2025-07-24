const iframeManager = {
	tabsEl: document.getElementById('tabs'),
	iframeContainer: document.getElementById('iframe-container'),
	scrollLeftBtn: document.getElementById('scroll-left'),
	scrollRightBtn: document.getElementById('scroll-right'),

	tabs: [],

	createTab(id, title, url) {
		if (this.tabs.find(t => t.id === id)) {
			this.activateTab(id);
			return;
		}
		this.tabs.push({ id, title, url });
		this.renderTabs();
		this.addIframe(id, url);
		this.activateTab(id);
	},

	removeTab(id) {
		const idx = this.tabs.findIndex(t => t.id === id);
		if (idx === -1) return;
		this.tabs.splice(idx, 1);

		const iframe = document.getElementById('iframe_' + id);
		if (iframe) iframe.remove();

		if (this.tabs.length === 0) {
			this.renderTabs();
			return;
		}

		const activeTab = this.tabsEl.querySelector('.tab.active');
		if (activeTab && activeTab.dataset.id === id) {
			const nextTab = this.tabs[idx] || this.tabs[idx - 1];
			this.activateTab(nextTab.id);
		}
		this.renderTabs();
	},

	activateTab(id) {
		const allTabs = this.tabsEl.querySelectorAll('.tab');
		allTabs.forEach(tab => {
			tab.classList.toggle('active', tab.dataset.id === id);
		});

		const iframes = this.iframeContainer.querySelectorAll('iframe');
		iframes.forEach(iframe => {
			iframe.style.display = (iframe.id === 'iframe_' + id) ? 'block' : 'none';
		});
		this.scrollToTab(id);
	},

	addIframe(id, url) {
		const iframe = document.createElement('iframe');
		iframe.id = 'iframe_' + id;
		iframe.src = url;
		iframe.style.display = 'none';
		iframe.style.width = '100%';
		iframe.style.height = '100%';
		iframe.style.border = 'none';
		this.iframeContainer.appendChild(iframe);
	},

	renderTabs() {
		this.tabsEl.innerHTML = '';
		this.tabs.forEach(tab => {
			const tabEl = document.createElement('div');
			tabEl.className = 'tab';
			tabEl.dataset.id = tab.id;
			tabEl.textContent = tab.title;

			const closeBtn = document.createElement('span');
			closeBtn.className = 'close-btn';
			closeBtn.textContent = '×';
			closeBtn.title = '닫기';
			closeBtn.onclick = e => {
				e.stopPropagation();
				this.removeTab(tab.id);
			};
			tabEl.appendChild(closeBtn);

			tabEl.onclick = () => this.activateTab(tab.id);
			this.tabsEl.appendChild(tabEl);
		});
	},

	scrollToTab(id) {
		const tabEl = this.tabsEl.querySelector(`.tab[data-id="${id}"]`);
		if (!tabEl) return;

		const tabBarRect = this.tabsEl.getBoundingClientRect();
		const tabRect = tabEl.getBoundingClientRect();

		if (tabRect.left < tabBarRect.left) {
			this.tabsEl.scrollBy({ left: tabRect.left - tabBarRect.left - 10, behavior: 'smooth' });
		} else if (tabRect.right > tabBarRect.right) {
			this.tabsEl.scrollBy({ left: tabRect.right - tabBarRect.right + 10, behavior: 'smooth' });
		}
	},

	init() {
		this.scrollLeftBtn.onclick = () => {
			this.tabsEl.scrollBy({ left: -150, behavior: 'smooth' });
		};
		this.scrollRightBtn.onclick = () => {
			this.tabsEl.scrollBy({ left: 150, behavior: 'smooth' });
		};

		// 이벤트 위임 방식으로 메뉴 클릭 처리
		document.getElementById('sidebar').addEventListener('click', e => {
			const li = e.target.closest('li');
			if (!li) return;
			const url = li.dataset.url;
			const title = li.dataset.title;
			if (url && title) {
				const id = btoa(url);
				this.createTab(id, title, url);
			}
		});
	}
};

document.addEventListener('DOMContentLoaded', () => {
	iframeManager.init();
});