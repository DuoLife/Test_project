package StringCharset;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * 中文和Unicode转换
 * @author zhangming
 *
 */
public class StringUnicode {

	private String str = "楼上";
	
	public static void main(String[] args) {
		StringUnicode strUnicode = new StringUnicode();
		System.out.println("十六进制unicode码	<-->	中文");
		System.out.println("开始，请输入文字。\n\t输入'\\exit'退出系统。");
		Scanner sc = new Scanner(System.in);
		
		while(true){
			//strUnicode.str = sc.next();
			if("\\exit".equals(strUnicode.str)){
				System.out.println("程序结束");
				System.exit(0);
			}
			System.out.println("\n------------------------------------------");
			System.out.println("输入字符：\n\t" + strUnicode.str);
			System.out.println("\n--------------\n");
			System.out.println("输出字符：");	System.out.print("\t");
			String[] list = strUnicode.str.split("\\\\u");
			int i,j;
			for(i = 0;i < list.length;i ++){
				if(list[i] != null && !"".equals(list[i])){
					try{
						if(list[i].length() < 4){
							int a = Integer.valueOf(list[i],16);
							System.out.print((char) a);
						}else{							
							int a = Integer.valueOf(list[i].substring(0,4),16);
							System.out.print((char) a);
							System.out.print(list[i].substring(4));;
						}
					}catch (Exception e){
						for(j = 0;j < list[i].length();j ++){
							System.out.print("\\u"+Integer.toHexString(list[i].charAt(j)));
			}	}	}	}
			System.out.println("\n------------------------------------------");
			strUnicode.str = "";
			break;
}	}	}
