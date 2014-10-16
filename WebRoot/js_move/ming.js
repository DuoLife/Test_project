/*
 * author: xuming
 * 
 * date: 2014.07.02
 * 
 * email: vip6ming@126.com
 */
/*
 * ajax 封装
 *
 * 1.创建Ajax
 * 2.创建连接
 * 3.发送请求
 * 4.接受信息
 *
 * @params(请求连接, 参数字符串, 请求成功回调函数, 请求失败回调函数)
 *
 */
 function Ajax(url, str, fnsuces, fnfailed) {
    var oAjax = window.XMLHttpRequest? new XMLHttpRequest(): new ActiveXObject("Microsoft.XMLHTTP");
    oAjax.open('GET', url + '?' + str, 'true');
    oAjax.send();
    oAjax.onreadystatechange = function () {
        if(oAjax.readyState == 4) {
            if(oAjax.status == 200){
                fnsuces && fnsuces(oAjax.responseText);
            }else {
                fnfailed && fnfailed();
            }
        }
    };
}
 
/*
 * 移动函数
 * 
 * @params (对象， 变化的style属性值， 步长变量， 目标位置， 增加回调函数)
 */
function doMove ( obj, attr, dir, target, endFn) {
	clearInterval( obj.timer);
	dir = target>parseInt(getStyle( obj, attr))?dir:-dir;
	obj.timer = setInterval( function () {
		var speed = parseInt(getStyle( obj, attr)) + dir;
		if( dir > 0 && speed > target || dir < 0 && speed < target) {
			speed = target;
		}
		obj.style[attr] = speed + 'px';
		if( speed == target ) {
			clearInterval( obj.timer);
			endFn && endFn();
		}
	}, 20);
}

/*
 * 获取元素style值
 * 由于document.getElementById(eId).style.XX只能获取dom中的属性，内联样式表中的值并不能通过此方法获取，而使用getStyle可以获取该元素真正的样式属性值。
 * 
 * 兼容IE：IE浏览器不支持getComputedStyle(obj)[attr]，而是自己实现了obj.currentStyle[attr]，效果相同。
 * 
 * @params (对象， 想要获取的style属性)
 */
function getStyle( obj, attr ) {return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj)[attr]}

/*
功能：
	点击li标签实现图片切换。
注意：
	最开始忘记写var，导致只有最后一次传递进来的id对应的元素有效。
	因为每次调用方法都将id对应的元素付给同一个变量，所以之前的就会被覆盖。
HTML结构：
	<div id="pic1" class="box">
		<p class="count">图片数量加载中。。。</p>
		<img src=""/>
		<p class="desc">图片描述加载中。。。</p>
		<ul>
		</ul>
	</div>
参数：
	1.对应父级元素的id。
	2.图片URL数组。
	3.图片对应描述的数组。
	4.li元素的图片切换事件触发方式。
使用：
	imageSwitch('pic2', ['img/01.png', 'img/02.png', 'img/03.png'], ['第1张照片', '第2张照片', '第3张照片'], 'onclick');
*/
function imageSwitch(targetId, arrImg, arrP, evt) {
	
	var oDiv = document.getElementById(targetId, arrImg, arrP);
	var oP1 = oDiv.getElementsByTagName('p')[0];
	var oP2 = oDiv.getElementsByTagName('p')[1];
	var oImg = oDiv.getElementsByTagName('img')[0];
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var oLi = oUl.getElementsByTagName('li');
	
	var num = 0;
	var len = arrImg.length;
	var flag = 0;
	for(var i=0; i<len; i++) {
		oUl.innerHTML += '<li></li>'
	}
	change();
	
	for(var i=0; i<len; i++) {
		oLi[i].index = i;
		oLi[i][evt] = function () {
			num = this.index;
			change();
		};
	}
	function change() {
		oLi[flag].className = '';
		flag = num;
		oLi[num].className = 'active';
		oP1.innerHTML = num+1 + '/' + len;
		oP2.innerHTML = arrP[num];
		oImg.src = arrImg[num];
	}
}

/*
注意：

HTML结构：
	其实只需要将ul的结构即可，js会自动填充内容并赋予相应的属性，使其移动。
	<input type="button" value="←"/>
	<div id="wrapper">
		<ul id="ul">
		</ul>
	</div>
参数：
	1.对应父级元素的id。
	2.图片URL数组。
	3.每次轮播移动几张图片
使用：
	carouselImage("ul", ['img/01.png', 'img/02.png', 'img/03.png'], 1);
*/

function carouselImage( id, arrImg, num, evt) {
	var oUl = document.getElementById(id);
	var oLi = oUl.getElementsByTagName('li');
	num = Number(num);
	var doWhat = 'onclick';
	evt && (doWhat = evt);
	for(var i=0; i<arrImg.length; i++) {
		oUl.innerHTML += "<li><img src='"+ arrImg[i] +"'/></li>";
	}
	getWidth( oUl, oLi);
	var oBtn = document.getElementsByTagName('input')[0];
	oBtn.flag = false;
	oBtn[doWhat] = function () {
		if(!oBtn.flag) {
			oBtn.flag = true;
			for( var i=0; i<num; i++) {
				var li = oUl.children[i].cloneNode(true);
				oUl.appendChild(li);
				getWidth( oUl, oLi);
			}
			doMove( oUl, 'left', 10, - num * 300, function () {
				for(var j=0; j<num; j++) {
					oUl.removeChild(oLi[0]);
				}
				oUl.style.left = 0;
				oBtn.flag = false;
			});
		}
	};
}
function getWidth ( obj, subElement ) {
	var oneSize = subElement[0].offsetWidth + 100;
	obj.style.width = oneSize * subElement.length + 'px';
}


/*fileQuery是指input type为file的对象，在事件中使用this来替代，fakepath， 
比如:obj.onChange=function(){ 
var file_img=document.getElementById("img"); 
getPath(file_img,this,transImg); 
} 
transImg是解决IE左上角图标的那张透明图片的路径;*/
function getPath(obj,fileQuery){ 
	if(window.navigator.userAgent.indexOf("MSIE")>=1){ 
		fileQuery.select(); 
		var path=document.selection.createRange().text; 
		obj.removeAttribute("src"); 
		obj.setAttribute("src",path); 
		//obj.style.filter= "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path+"', sizingMethod='scale');";  
	}else { 
		var file =fileQuery.files[0];  
		var reader = new FileReader();  
		reader.onload = function(e){ 
		obj.setAttribute("src",e.target.result) 
		} 
		reader.readAsDataURL(file);  
	}
} 