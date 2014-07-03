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
	}, 30);
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
