package javaFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class File {

	public static void main(String[] args) {
		String infile = "F:/workspace/Test_project/src/javaFile/a.txt";
		String outfile = "F:/workspace/Test_project/src/javaFile/b.txt";
		byte[] tempbyte = new byte[100];
		int byteread = 0;
		InputStream in = null;
		FileOutputStream out = null;
		try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[1000];
            in = new FileInputStream(infile);
            out = new FileOutputStream(outfile);
            System.out.println(in.available());
            System.out.println(System.currentTimeMillis());
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
            	out.write(tempbytes, 0, byteread);
                //System.out.write(tempbytes, 0, byteread);
            }
            System.out.println(System.currentTimeMillis());
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
            }
            if (out != null) {
            	try {
            		out.close();
            	} catch (IOException e1) {
            }
        }
      }
    }
  }
}
