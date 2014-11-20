// 3d-rollimg 
function rollimg3D() {
	var wrapDiv = document.getElementById('rollimg_wrap');
	var aUl = wrapDiv.getElementsByTagName('ul');
	var oBtns = wrapDiv.getElementsByTagName('input');
	//get li rows,ceils
	var iRows = wrapDiv.offsetHeight/aUl[0].children[0].offsetHeight;
	var iCeils = wrapDiv.offsetWidth/aUl[0].children[0].offsetWidth;
	var liNum = iRows * iCeils;
	var liStr = '';
	for(var n=0; n<liNum; n++) {
		liStr += '<li></li>';
	}
	var iNow = 0;
	var aXy = [];
	for(var i=0; i<aUl.length; i++) {
		aUl[i].innerHTML = liStr;
		aUl[i].style.zIndex = aUl.length - i;
		aXy.push(setXy(aUl[i].children,iRows,iCeils));
	}
	//var arrLi = setXy(aLi, iRows, iCeils);
	//btn bind
	oBtns[1].onclick = function () {
		if(iNow >= aUl.length-1) {
			return;
		}
		tab(aXy[iNow], iRows-1, iCeils-1, -1, function () {
				with(this.style) {
					transition = '.2s border, 1s .5s background, 1s -webkit-transform ease-in, 1s opacity';
					borderColor="rgba(0,0,0,0.6)";
					boxShadow='0 0 20px #666666';
					WebkitTransform="translate(-800px, -500px) rotateX(720deg) rotateY(-360deg)";
					opacity=0;
				}
			}, 50);
			iNow++;
	};
	oBtns[0].onclick = function () {
		if(iNow <= 0) {
			return;
		}
		iNow--;
		tab(aXy[iNow], 0, 0, 1, function () {
				with(this.style) {
					transition = '1s border, 1s background, .5s -webkit-transform ease-out, .8s opacity';
					borderColor="rgba(0,0,0,0)";
					boxShadow='0 0 00px #666666';
					WebkitTransform="translate(0px,0px) rotateX(0deg) rotateY(0deg)";
					opacity=1;
				}
			}, 50);
	};
	//
	function tab(obj, x, y, iSpeed, fn, iDelay) {
		if(!obj[x] || !obj[x][y]) {
			return;
		}
		if(fn) {
			fn.call(obj[x][y]);
			clearTimeout(obj[x][y].timer);
			obj[x][y].timer = setTimeout(function () {
				tab(obj, x+iSpeed, y, iSpeed, fn, iDelay);
				tab(obj, x, y+iSpeed, iSpeed, fn, iDelay);
			}, iDelay);
		}
	}
	//alert(iRows + " : " + iCeils);
	function setXy(obj, iRows, iCeils) {
		var arrLi = [];
		for(var i=0; i<iRows; i++) {
			var arr2 = [];
			for(var j=0; j<iCeils; j++) {
				//console.log(i*iCeils+j);
				obj[i*iCeils+j].xIndex = i;
				obj[i*iCeils+j].yIndex = j;
				obj[i*iCeils+j].style.backgroundPosition = -j*60 + 'px -' + i*60 + 'px';
				arr2.push(obj[i*iCeils+j]);
			}
			arrLi.push(arr2);
		}
		return arrLi;
	}
}