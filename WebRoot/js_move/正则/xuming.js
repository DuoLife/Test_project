/*
  author: xuming
  date: 2014/10/16
  email: vip6ming@126.com
*/
function Re_regeister() {
	//注册三项信息的匹配规则
	var nameRE = /^[\w_\u4e00-\u9fa5]{2,8}$/;  //用户名：只允许数字，字母，下划线（—），中文。长度2-8位。
	var pwRE = /^(?=.*[a-zA-Z0-9]).{6,12}$/;  //密码：数字，字母。
	var emailRE = /^\s*\w+(?:\.{0,1}[\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\.[a-zA-Z]+\s*$/;
	//元素获取
	var oUname = document.getElementById('uname_r');
	var oPw = document.getElementById('pwd');
	var oEmail = document.getElementById('email_r');
	
	oUname.onblur = function () {
		reTest(nameRE, oUname.value);
	};
	oPw.onblur = function () {
		reTest(pwRE, oPw.value);
	};
	oEmail.onblur = function () {
		reTest(emailRE, oEmail.value);
	};
	
	//校验并提示
	function reTest(re, str) {
		if( ! re.test(str)) {
			alert('输入不合法~');
		}
	}
}
/*
  author: xuming
  update: 2014/10/22
  email: vip6ming@126.com
*/
//运动框架
function videoListMove(obj, attr, iTarget) {
	clearInterval(obj.timer);
	obj.timer = setInterval(function () {
		var iCur = parseInt(getStyle(obj, attr));
		var iSpeed = (iTarget - iCur)/12;
		var objHeight = parseInt(getStyle(obj, 'height'));
		iSpeed = iSpeed>0 ? Math.ceil(iSpeed): Math.floor(iSpeed);
		obj.style[attr] = iCur + iSpeed + 'px';
		if(iCur + iSpeed >= 0) {
			clearInterval(obj.timer);
			obj.style[attr] = '0px';
			isWork = false;
		}else if(iCur + iSpeed <= -(objHeight - 5*100)) {
			clearInterval(obj.timer);
			obj.style[attr] = -(objHeight - 5*100) + 'px';
			isWork = false;
		}
		if(iCur == iTarget) {
			clearInterval(obj.timer);
			isWork = false;
		}
	}, 30);
}
function getStyle(obj, attr) {
	return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj)[attr];	
}