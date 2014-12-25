//禁止原生滑动
document.ontouchmove = function(ev){
	 ev.preventDefault();
};
//alert(' w : ' + document.body.offsetWidth + ' w : ' + document.body.offsetHeight);
//init
var list = document.getElementById('list');
setWidth(list, 'li');
//滑动部分
var downX;
var downy;
var iNow=0;
var eleNum = 0;
var audio = document.getElementById('audio');
var musicBtn = document.getElementById('musicBtn');
addEvent(list, 'touchstart', pageSlip);
function pageSlip(ev) {
	//console.log(ev);
	downX = ev.changedTouches[0].pageX;
	downY = ev.changedTouches[0].pageY;
	//console.log(downX);
	addEvent(this, 'touchmove', pageMove);
	addEvent(this, 'touchend', pageMoveEnd);
}
function pageMove(ev) {
	var touch = ev.changedTouches[0];
	var nowX = touch.pageX;
	//console.log(nowX-downX);
	var w = document.body.offsetWidth;
	var iSpeed = nowX - downX + (-iNow * w);
	if(iNow == 0) {
		nowX = touch.pageX;
		iSpeed = (nowX - downX)/3 + (-iNow * w);
	}
	if(iNow == list.children.length-1) {
		nowX = touch.pageX;
		iSpeed = (nowX - downX)/3 + (-iNow * w);
	}
	this.classList.remove('listMove');
	this.classList.add('listTouch');
	with(this.style) {
		transform = 'translate('+ iSpeed +'px,0)';
		WebkitTransform = 'translate('+ iSpeed +'px,0)';
	}
}
function pageMoveEnd(ev) {
	//console.log(iNow);
	var nowX = ev.changedTouches[0].pageX;
	//console.log(list.children.length);
	//console.log(nowX-downX + '... iNow: ' + iNow);
	//移除active
	removeActive(list, iNow);
	if(nowX-downX > 30) {
		if(iNow-1 >= 0) {
			iNow--;
		}
	}else if(nowX-downX < -30) {
		if(iNow <= list.children.length-2) {
			iNow++;
			if(iNow == (eleNum - 1)) {
				hiddenSlipBtn();
			}
		}
	}
	if(iNow == 1) {
		audio.play();
		//console.log('lll');
		//alert(iNow+'....');
	}
	//添加active
	addActive(list, iNow);
	var w = document.body.offsetWidth;
	this.classList.remove('listTouch');
	this.classList.add('listMove');
	with(this.style) {
		transform = 'translate('+ (-iNow * w) +'px,0)';
		WebkitTransform = 'translate('+ (-iNow * w) +'px,0)';
	}
	removeEvent(this, 'touchmove', pageMove);
	removeEvent(this, 'touchend', pageMoveEnd);
}
//显示动画 active
function addActive(obj, n) {
	var oEle = obj.children[n];
	oEle.classList.add('active');
}
function removeActive(obj, n) {
	var oEle = obj.children[n];
	oEle.classList.remove('active');
}
//音乐控制
var audio = document.getElementById('audio');
var musicBtn = document.getElementById('musicBtn');
var isPlay = false;
addEvent(musicBtn, "touchstart", function () {
	addEvent(musicBtn, "touchend", audioControl);
});
function audioControl() {
	if(isPlay) {
		audio.pause();
		musicBtn.style.backgroundPosition = '-30px 0';
		isPlay = false;
	}else {
		audio.play();
		musicBtn.style.backgroundPosition = '0 0';
		isPlay = true;
	}
	removeEvent(musicBtn, "touchend", audioControl);
}
//页面尺寸变化时重新设置ul以及li宽度
function show() {
	setWidth(list, 'li');
}
//设置ul宽度，全部li横向排列，便于横向滑动
function setWidth(obj, attr) {
	var aSubE = obj.getElementsByTagName(attr);
	var num = aSubE.length;
	//console.log(num);
	if(num == 0) {
		return;
	}
	var w = document.body.offsetWidth;
	for(var i=0; i<num; i++) {
		aSubE[i].style.width = w + 'px';
	}
	var subWidth = parseInt(getStyle(aSubE[0], 'width'));
	obj.style.width = subWidth * num + 'px';
}
//loading
//var urlPath = 'http://192.168.199.146:2103/Test_project/js_move/html5mobile/';
loading_ani();
//loading timeout 
requestTimeout();

var isOnload = false;
function loading_ani() {
	//music onload
	//console.log('loading_ani');
	var aImg = ['nian/zi.png','nian/supfont1.png','nian/supfont2.png','nian/16_ed.png','nian/0.png','nian/1.png','nian/2.png','nian/3.png','nian/4.png','nian/5.png','nian/6.png','nian/7.png','nian/8.png','nian/9.png','nian/10.png','nian/11.png','nian/12.png','nian/13.png','nian/14.png','nian/15.png','nian/16.png'];
	eleNum = aImg.length;
	var onloadedNum = 0;
/*	addEvent(audio, 'canplaythrough', function () {
		audio.play();
		console.log('load music');
		onloadedNum++;
		if(onloadedNum == eleNum) {
			isOnload = true;
			//showBeginBtn();
			hiddenProgressPage();
		}
	});*/
	//img onload
	for(var i=0; i<aImg.length; i++) {
		//var oSrc = urlPath + aImg[i];
		var oSrc = aImg[i];
		//console.log(oSrc);
		var img = new Image();
		img.src = oSrc;
		addEvent(img, 'load', function () {
			onloadedNum++;
			//console.log(onloadedNum);
			var precent = Math.ceil((onloadedNum / eleNum) * 100);
			updateProgressBar(precent);
			if(onloadedNum == eleNum) {
				isOnload = true;
				//showBeginBtn();
				hiddenProgressPage();
			}
		});
	}
}
var oBeginBtn = document.getElementById('lengtoo_begin');
var oSlipBtn = document.getElementById('slipBtn');
var progressBar = document.getElementById('progress_bar');
var progressBox = document.getElementById('progress_box');
addEvent(oBeginBtn, 'touchend',  hiddenProgressPage);
//showBeginBtn();
function showBeginBtn() {
	progressBox.classList.add('progressBox_hidden');
	oBeginBtn.classList.remove('lengtoo_begin');
	oBeginBtn.classList.add('showBeginBtn');
}
//progress hidden
function hiddenProgressPage() {
	var progressPage = document.getElementById('progress_page');
	progressPage.classList.add('page_hidden');
	oSlipBtn.classList.add('slip_ani');
	addActive(list, iNow);
	audioControl();
}
function hiddenSlipBtn() {
	oSlipBtn.classList.remove('slip_ani');
}
//updateProgressBar();
function updateProgressBar(precent) {
	//console.log(precent);
	progressBar.style.width = precent + '%';
	if(100 == precent) {
		isOnload = true;
		showBeginBtn();
	}
}
//timeout 
function requestTimeout() {
	setTimeout(function () {
		if(isOnload == false) {
			//alert("网络不好，刷新下试试~");
			//此处改为直接进入；
			//showBeginBtn();
			//console.log('15...');
			hiddenProgressPage();
		}
	}, 15000);
}
//huluobo
var hlb = document.getElementById('huluobo');
var lengtoo16p = document.getElementById('lengtoo16p');
addEvent(hlb, 'touchstart', fnHLBStart);
function fnHLBStart (ev) {
	ev.preventDefault();
	lengtoo16p.style.background = 'url(nian/16_ed.png)';
	lengtoo16p.style.backgroundSize = '320px 320px';
	addEvent(hlb, 'touchend', fnHLBEnd);
}
function fnJump () {
	window.location.href='http://mp.weixin.qq.com/s?__biz=MzAxNzE0MDAzOA==&mid=202494175&idx=1&sn=b565e6bcbf4037eb3774c8c637b693fd#rd';
}
function fnHLBEnd (ev) {
	ev.preventDefault();
	lengtoo16p.style.background = 'url(nian/16.png)';
	lengtoo16p.style.backgroundSize = '320px 320px';
	addEvent(hlb, 'touchstart', fnJump);
	removeEvent(hlb, 'touchstart', fnHLBStart);
	removeEvent(hlb, 'touchend', fnHLBEnd);
}
function getStyle(obj, attr) {
	return obj.currentStyle?obj.currentStyle()[attr]:getComputedStyle(obj)[attr];
}
function addEvent(obj, attr, fn) {
	obj.attachEvent?obj.attachEvent('on' + attr, fn):obj.addEventListener(attr, fn, false);
}
function removeEvent(obj, attr, fn) {
	obj.detachEvent?obj.detachEvent('on' + attr, fn):obj.removeEventListener(attr, fn, false);
}