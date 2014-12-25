var appId = 'wxaccdc15d188edb90';
var imgUrl = 'http://shengdan.lengtoo.com/nian/120.jpg';
var link = 'http://shengdan.lengtoo.com/pandianing.html';
var shareInfo = '2014年度最最流行语！不想out？快来get!';
var title = '冷兔年度流行语盘点';
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	// 发送给好友
	WeixinJSBridge.on('menu:share:appmessage', function(argv){
		WeixinJSBridge.invoke('sendAppMessage',{
							  "appid":appId,
							  "img_url":imgUrl,
							  "img_width":"120",
							  "img_height":"120",
							  "link":link,
							  "desc":shareInfo,
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
							  "desc":shareInfo,
							  "title":title
							  }, function(res) {
							  });
	});
	
	// 分享到微博
	WeixinJSBridge.on('menu:share:weibo', function(argv){
		WeixinJSBridge.invoke('shareWeibo',{
							  "content":shareInfo,
							  "url":'http://shengdan.lengtoo.com/pandianing.html',
							  }, function(res) {
							  });
	});
}, false);