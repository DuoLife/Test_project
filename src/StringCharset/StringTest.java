package StringCharset;


public class StringTest {

	public static void main(String[] args) {
//		String str = new String("abc");
//		String str1 = "abc";
//		String a = str;
//		System.out.println("abc" == str);
//		System.out.println(str == a);
		
		String str = new String("abc");
		String a = "ab";
		String str1 = "ab"+"c";
		System.out.println("abc" == str1);
		System.out.println(str1);
		
		
		System.out.println(Math.round(Math.random()*6));
	}
}
