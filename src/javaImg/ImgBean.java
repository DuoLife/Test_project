package javaImg;

import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
import java.awt.image.*; 
import org.w3c.dom.*; 
import com.sun.image.codec.jpeg.*; 
import javax.imageio.*; 

public class ImgBean{ 
	public void ImgBean(){} 
	public void ImgYin(String s,String ImgName){ 
		try{ 
			File _file = new File(ImgName); 
			Image src = ImageIO.read(_file); 
			int wideth=src.getWidth(null); 
			int height=src.getHeight(null); 
			BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB); 
			Graphics g=image.createGraphics(); 
			g.drawImage(src,0,0,wideth,height,null); 
			//String s="我要加的水印"; 
			g.setColor(Color.RED); 
			g.setFont(new Font("宋体",Font.PLAIN,60)); 
			Font aa=new Font("宋体",Font.PLAIN,20); 
			
			g.drawString(s,wideth-150,height-10); 
			g.dispose(); 
			//String 
			FileOutputStream out=new FileOutputStream(ImgName); 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
			encoder.encode(image); 
			out.close(); 
		} 
		catch(Exception e){ 
			System.out.println(e); 
		} 
	} 
}
