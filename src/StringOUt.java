import java.text.SimpleDateFormat;
import java.util.Date;


public class StringOUt {
	public static void main(String[] args) {
	 //String[] arr = { "changba", "momobiye", "momodanshen", "QQchunjie", "QQshijiebei", "QQshijin", "QQshuajianpian"};
     //String[] arr = { "line","baidumotu","kechenggezi","QQlengtuguoshuangjie","QQshangcheng"};
//	Object[][] arr = { {"chaohaipian",8,2},{"feizaoju",8,3},{"jingxiapian",8,4},{"lolxxs",20,1},{"maimaimaipian",12,5},{"moxingtaocan001",8,6},{"ruantangpian",20,7},{"shendoushi",8,8},{"yeshizuile",16,9}};
//	Object[][] arr = { {"baidumotu",24,1},{"chihuo",24,2},{"huanle",24,3},{"shipin",24,4}};
//	Object[][] arr = { {"lineshang",16, 5, "冷兔line篇上", "又萌又贱的冷兔登录LINE贴图商城，亲们也可以用它们来装扮自己的照片哦~~"},{"linexia",16,6, "冷兔line篇下", "又萌又贱的冷兔登录LINE贴图商城，亲们也可以用它们来装扮自己的照片哦~~"},{"lvxing",16,7, "冷兔旅行篇", "带着冷兔一块去旅行吧，旅行篇贴纸双爪奉上！一定要使用哦~"}};
	Object[][] arr = { {"shuangshiyi",24, 11, "冷兔 “买买买双11特别篇”", "购物车又满了，就等双十一剁手了！"}};
//	Object[][] arr = { {"kakao",24,10}};
	int id = 133;
	Date date = new Date();
	 for(Object[] o: arr) {
//		 System.out.print("[");
		 for(int i=0;i<(Integer)o[1];i++) {
			 date.setSeconds(date.getSeconds()+10);
//			 System.out.print(", '/lengtu/img/download/tietu/"+ o[0] + "/"  +i+".png'");
			 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 System.out.print("INSERT INTO `lengtoo_emoji` VALUES ('"+id+"', '"+o[2]+"', '/img/emoji/emoji/"+o[0]+"/"+i+".png', '冷兔表情', '"+sf.format(date)+"', '1', '200', '200', '1', '0');");
//			 System.out.print("INSERT INTO `lengtoo_chartletpackage` VALUES ('"+o[2]+"', '/img/chartlet/chartlet/packageimg/"+o[0]+".png', '"+ o[3] +"', '"+ o[4] +"', '"+sf.format(date)+"', '1', '"+o[1]+"', '2', '90', '90', '1', '1', '1');");
//			 System.out.print("INSERT INTO `lengtoo_chartlet` VALUES ('"+id+"', '"+o[2]+"', '/img/chartlet/chartlet/"+o[0]+"/"+i+".png', '冷兔贴图', '"+sf.format(date)+"', '1', '200', '200', '1', '0');");
//			 System.out.print("INSERT INTO `lengtoo_chartletillustration` VALUES ('"+id+"', '"+o[2]+"', '/img/chartlet/illustration/"+o[0]+i+".jpg', '冷兔表情', '"+sf.format(date)+"', '640', '320', '1', '0');");
			 id++;
		 }
		 System.out.println("INSERT INTO `lengtoo_emojirollimg` VALUES ('', '/img/emoji/rollimg/ruantangpian.jpg', '640', '240', '冷兔表情', '买买买双11特别篇', '1', '10', '2014-11-11 10:54:14', '1', '0');");
//		 System.out.println("],");
//		 System.out.println("'/lengtu/img/download/biaoqing/"+ o + "/"  +o+".zip");
	 }	
	 boolean test = false;
	 //System.out.println(test);
	 org.apache.commons.io.FileUtils f = new org.apache.commons.io.FileUtils();
	}
}
