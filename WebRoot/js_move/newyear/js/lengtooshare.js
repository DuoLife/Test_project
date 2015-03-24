var appId = 'wxaccdc15d188edb90';
var imgUrl = 'http://shengdan.lengtoo.com/nian/120.jpg';
var link = 'http://shengdan.lengtoo.com/yuanxiao/newyear/select.html';
var shareInfo = [
	"1冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"2冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"3冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"4冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"5冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"6冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"7冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"8冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"9冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"10冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"11冷兔十二生肖闹元宵冷兔十二生肖闹元宵",
	"12冷兔十二生肖闹元宵冷兔十二生肖闹元宵"
];
var title = '冷兔十二生肖闹元宵';
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	// 发送给好友
	WeixinJSBridge.on('menu:share:appmessage', function(argv){
		WeixinJSBridge.invoke('sendAppMessage',{
							  "appid":appId,
							  "img_url":imgUrl,
							  "img_width":"120",
							  "img_height":"120",
							  "link":link,
							  "desc":shareInfo[window.InfoNum],
							  "title":title
							  }, function(res) {
							  })
	});
	
	// 分享到朋友圈
	WeixinJSBridge.on('menu:share:timeline', function(argv){
		WeixinJSBridge.invoke('shareTimeline',{
							  "img_url":imgUrl,
							  "img_width":"120",
							  "img_height":"120",
							  "link":link,
							  "desc":title,
							  "title":shareInfo[window.InfoNum]
							  }, function(res) {
							  });
	});
	
	// 分享到微博
	WeixinJSBridge.on('menu:share:weibo', function(argv){
		WeixinJSBridge.invoke('shareWeibo',{
							  "content":shareInfo,
							  "url":'http://shengdan.lengtoo.com/yuanxiao/newyear/select.html',
							  }, function(res) {
							  });
	});
}, false);