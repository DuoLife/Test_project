/**
* <p>Title: ByteTest.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-8-13
* @version 1.0
*/
package com.imgupload.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>Title: ByteTest.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-8-13
 * Email: vip6ming@126.com
 */
public class ByteTest {

	public static void main(String[] args) {
//		byte b[] = new byte[20];
//		b[0] = 97;
//		String str = "a bc";
//		//b = str.getBytes();
//		for(byte be: b) {
//			System.out.print(be + "  ");
//		}
		
		//文件读取与写入，测试下读出来的到底什么。a->97.
		getByte();
	}
/**
 * 文件的读取与写入
 * 
 * 1.文件的读取与写入都是以二进制流的形式来进行的。
 * 	  当文件很大，便不能一次性的全部读入到内存中进行操作，这时就需要需要一个缓冲区来存放这些数据，缓冲区满了
 *   就将这些数据写入到目标文件。再将新数据写入缓冲区，如此往复，直到全部读取完毕。
 * 2.缓冲区。
 *   使用byte数组（byte[] buffer = new byte[4*1024];）设置缓存区的大小对文件的读取写入速度是有影响的，
 *   但不是越大越大，值过大会引起内存溢出。(new byte[]操作是需要申请堆内存的,myeclipse默认情况下
 *   80*1024*1024:80M就会java.lang.OutOfMemoryError: Java heap space )
 * 3.编码。
 *   如果读取的是文档类的，或是字符流，想要获取其中的文字，可以有 “new String(buffer)”.
 *   	有时会出现乱码，这时你需要确定两点：
 *   		1.文档或字符流的原本编码是否支持中文，编码格式为。
 *   		2.转换为字符串时设定字符编码为原编码格式相符。如：原编码为Unicode，则  new String(buffer, "Unicode");
 * 另：
 *   如果想要输出二进制，看看流原本的样子，Integer.toBinrayString(*);参数可以是char，byte等。
 * 
 */
	
	/**
	 * 
	 * @author xuming
	 * 
	 * @param 
	 * 
	 * @return 
	 * 
	 * @date 2014-8-13
	 */
	public static void getByte() {
		try {
			File _file = new File("F://a.txt");
			File _copyFile = new File("F://a_copy.txt");
//			File _file = new File("F://img.jpg");
//			File _copyFile = new File("F://img_copy.jpg");
			FileInputStream in = new FileInputStream(_file);
			FileOutputStream out = new FileOutputStream(_copyFile);
			byte[] buffer = new byte[1*1024];
			int i = 0;
			String str = "";
			StringBuffer sb = new StringBuffer("abc");
			while ((i = in.read(buffer)) !=-1) {
//				str = new String(buffer,0,i, "utf-8");
				for(byte b: buffer) {
					sb.append(b+" ");
				}
				str = new String(buffer,0,i);
				System.out.println(buffer.length);
				out.write(buffer, 0, i);
			}
			System.out.println(sb + " " + str);
//			for(byte b: buffer) {
//				sb.append(Integer.toBinaryString(b)+"");
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
