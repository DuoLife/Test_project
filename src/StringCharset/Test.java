package StringCharset;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Test {
	 /**
	  * 字符串编码转换的实现方法
	  * @param str  待转换编码的字符串
	  * @param oldCharset 原编码
	  * @param newCharset 目标编码
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public String changeCharset(String str, String oldCharset, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   //用旧的字符编码解码字符串。解码可能会出现异常。
	   byte[] bs = str.getBytes(oldCharset);
	   //用新的字符编码生成字符串
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
	 public static void main(String[] args) {
		 try {
			String str = "楼";
//			 byte[] bs = str.getBytes("UTF-8");
//			 for(byte b: bs) {
//				 System.out.println(b);
//			 }
//			 String UNIStr = new String(bs, "Unicode");
//			 byte[] Ub = UNIStr.getBytes();
//			 for(byte b: Ub) {
//				 System.out.println(b);
//			 }
//			 int a = Integer.valueOf(str, 16);
//			 System.out.println((char)a);
			System.out.println(Integer.toHexString(str.charAt(0)));
		 } catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
