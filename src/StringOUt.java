import java.text.SimpleDateFormat;
import java.util.Date;


public class StringOUt {
	public static void main(String[] args) {
	 //String[] arr = { "changba", "momobiye", "momodanshen", "QQchunjie", "QQshijiebei", "QQshijin", "QQshuajianpian"};
     //String[] arr = { "line","baidumotu","kechenggezi","QQlengtuguoshuangjie","QQshangcheng"};
//	Object[][] arr = { {"chaohaipian",8,2},{"feizaoju",8,3},{"jingxiapian",8,4},{"lolxxs",20,1},{"maimaimaipian",12,5},{"moxingtaocan001",8,6},{"ruantangpian",20,7},{"shendoushi",8,8},{"yeshizuile",16,9}};
//	Object[][] arr = { {"baidumotu",24,1},{"chihuo",24,2},{"huanle",24,3},{"shipin",24,4}};
//	Object[][] arr = { {"lineshang",16, 5, "冷兔line篇上", "又萌又贱的冷兔登录LINE贴图商城，亲们也可以用它们来装扮自己的照片哦~~"},{"linexia",16,6, "冷兔line篇下", "又萌又贱的冷兔登录LINE贴图商城，亲们也可以用它们来装扮自己的照片哦~~"},{"lvxing",16,7, "冷兔旅行篇", "带着冷兔一块去旅行吧，旅行篇贴纸双爪奉上！一定要使用哦~"}};
//	Object[][] arr = { {"guonianaipian", 9, 14, "过年癌篇", "过年了，你能抵过七大姑八大姨的超强战斗力吗？"},{"xinnianpian", 21, 15, "春节篇", "一大波冷兔新春表情到来"}};
//	Object[][] arr = { {"duangduang", 9, 16, "duangduang篇", "其实一开始duang，我是拒绝的。"}};
//	Object[][] arr = { {"bidongpian", 9, 17, "壁咚篇", "此处壁咚太生猛让你毫无还击之力。"}};
//	Object[][] arr = { {"fuliancos", 8, 18, "冷兔联盟", "冷兔化身成超级英雄们，你难道是猴子派来拯救世界的？"}};
//	Object[][] arr = { {"ertongjie", 16, 19, "冷兔过双节", "经典的游戏能否唤起你儿时的回忆？"}};
	Object[][] arr = { {"chaogu", 6, 20, "冷兔炒股", "涨涨涨，股市有风险，投资需谨慎！"}};
//	Object[][] arr = { {"chunjiepian", 16, 8, "春节篇", "萌萌的冷兔春节贴纸来了，快来装饰你的新年照片吧!"}};
//	Object[][] arr = { {"kakao",24,10}};
	int id = 247;
	Date date = new Date();
	 for(Object[] o: arr) {
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 System.out.print("[");
		 System.out.print("INSERT INTO `lengtoo_emojipackage` VALUES ('"+o[2]+"', '/img/emoji/packageimg/"+o[0]+".png', '"+ o[3] +"', '"+ o[4] +"', '"+sf.format(date)+"', '1', '"+o[1]+"', '2', '90', '90', '1', '1', '1','/img/emoji/emoji/com.coldworks.emoticon..zip');");
//		 System.out.print("INSERT INTO `lengtoo_chartletpackage` VALUES ('"+o[2]+"', '/img/chartlet/chartlet/packageimg/"+o[0]+".png', '"+ o[3] +"', '"+ o[4] +"', '"+sf.format(date)+"', '1', '"+o[1]+"', '2', '90', '90', '1', '1', '1');");
		 for(int i=0;i<(Integer)o[1];i++) {
			 date.setSeconds(date.getSeconds()+10);
//			 System.out.print(", '/lengtu/img/download/tietu/"+ o[0] + "/"  +i+".png'");
			 System.out.print("INSERT INTO `lengtoo_emoji` VALUES ('"+id+"', '"+o[2]+"', '/img/emoji/emoji/"+o[0]+"/"+i+".gif', '冷兔表情', '"+sf.format(date)+"', '1', '200', '200', '1', '0');");
//			 System.out.print("INSERT INTO `lengtoo_chartlet` VALUES ('"+id+"', '"+o[2]+"', '/img/chartlet/chartlet/"+o[0]+"/"+i+".png', '冷兔贴图', '"+sf.format(date)+"', '1', '200', '200', '1', '0');");
			 id++;
		 }
		 System.out.println("INSERT INTO `lengtoo_emojirollimg` VALUES ('', '/img/emoji/rollimg/"+o[0]+".jpg', '640', '240', '冷兔表情', '"+ o[4] +"', '1', '"+o[2]+"', '"+sf.format(date)+"', '1', '0');");
//		 System.out.println("INSERT INTO `lengtoo_chartletrollimg` VALUES ('', '/img/chartlet/rollimg/"+o[0]+".jpg', '640', '240', '冷兔表情', '"+ o[4] +"', '1', '"+id+"', '2014-11-11 10:54:14', '1', '0');");
//		 System.out.println("INSERT INTO `lengtoo_chartletillustration` VALUES ('"+id+"', '"+o[2]+"', '/img/chartlet/illustration/show.jpg', '冷兔表情', '"+sf.format(date)+"', '640', '320', '1', '0');");
//		 System.out.println("],");
//		 System.out.println("'/lengtu/img/download/biaoqing/"+ o + "/"  +o+".zip");
	 }	
	 boolean test = false;
	 //System.out.println(test);
	 org.apache.commons.io.FileUtils f = new org.apache.commons.io.FileUtils();
	}
}
