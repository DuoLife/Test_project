/**
* <p>Title: MyImage.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-8-12
* @version 1.0
*/
package javaImg;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <p>Title: MyImage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-8-12
 * Email: vip6ming@126.com
 */
public class MyImage {

	private Image img;
	private int width;
	private int height;
	
	public MyImage(String src) {
		try {
			File _file = new File(src);
			this.img = ImageIO.read(_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
