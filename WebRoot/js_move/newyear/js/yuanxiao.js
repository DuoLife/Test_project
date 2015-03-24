var a = document.getElementById('index_content'),
	c = document.getElementById('close'),
	s = a.getElementsByTagName('span'),
	img = document.getElementById('show_img'),
	oShowPage = document.getElementById('show_page'),
	oAlertPage = document.getElementById('alert_page'),
	oLoadPage = document.getElementById('load_page'),
	oAlertImg = document.getElementById('alert_img'),
	share_btn = document.getElementById('share_btn'),
	attention_btn = document.getElementById('attention_btn');
	
var pastCName = '',
addOnClick = function () {
	for (var i=0; i<s.length; i++) {
		s[i].index = i;
		addEvent(s[i], 'touchstart', spanOnclick);
	}
},
spanOnclick = function () {
	oLoadPage.classList.add('active_load');
	oShowPage.classList.add('showPage_active');
	oLoadPage.style.display = 'block';
	oShowPage.style.display = 'block';
	img.src = 'img/H503/'+this.index+'.png';
	window.InfoNum = this.index;
	//alert(this.index);
},
closeShowPagefn = function () {
	oLoadPage.classList.remove('active_load');
	oLoadPage.style.display = 'none';
	oShowPage.style.display = 'none';
},
sharefn = function () {
	pastCName = 'alert_share';
	oAlertImg.classList.add('alert_share');
	oAlertPage.style.display = 'block';
},
attentionfn = function () {
	window.location.href='http://mp.weixin.qq.com/s?__biz=MzAxNzE0MDAzOA==&mid=204697123&idx=1&sn=98939893400edaf5a8949362feb7cdcf#rd';
},
closeAlertfn = function () {
	oLoadPage.classList.remove('active_load');
	oAlertImg.classList.remove(pastCName);
	oAlertPage.style.display = 'none'
};
addEvent(oAlertPage, 'touchstart', addOnClick);
addEvent(c, 'touchstart', closeShowPagefn);
addEvent(share_btn, 'touchstart', sharefn);
addEvent(attention_btn, 'touchstart', attentionfn);
addEvent(oAlertPage, 'touchstart', closeAlertfn);
function addEvent(obj, attr, fn) {
	obj.attachEvent?obj.attachEvent('on' + attr, fn):obj.addEventListener(attr, fn, false);
}
function removeEvent(obj, attr, fn) {
	obj.detachEvent?obj.detachEvent('on' + attr, fn):obj.removeEventListener(attr, fn, false);
}
//####################//
addOnClick();