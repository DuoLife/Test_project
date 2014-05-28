package regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularDemo {

	public static void main(String[] args) {
//		String regular = "^java.*";
//		String input = "javahello world java lll";
//		Pattern pattern = Pattern.compile(regular);
//		Matcher matcher = pattern.matcher(input);
//		System.out.println(matcher.find());
//		System.out.println(matcher.matches());
		
		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.]+");
		Matcher matcher = pattern.matcher("dsdsds<http://ds.ds//gfgffdfd>fdfhttps://lengxiaohua.com");
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){              
		    buffer.append(matcher.group());        
		    buffer.append("\r\n");              
		}
		System.out.println("lianjie"+buffer.toString());
	}
}
