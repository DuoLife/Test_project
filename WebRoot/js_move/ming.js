/*
 * author: xuming
 * 
 * date: 2014.07.02
 * 
 * email: vip6ming@126.com
 */

/*
 * 移动函数
 * 
 * @params (对象， 变化的style属性值， 步长变量， 目标位置)
 */
function doMove ( obj, attr, dir, target) {
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
		}
	}, 30);
}
function getStyle( obj, attr ) {return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj)[attr]}
