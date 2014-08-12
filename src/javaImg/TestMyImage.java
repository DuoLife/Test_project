/**
* <p>Title: TestMyImage.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-8-12
* @version 1.0
*/
package javaImg;

/**
 * <p>Title: TestMyImage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-8-12
 * Email: vip6ming@126.com
 */
public class TestMyImage {

	//获取图片宽高
	public static void main(String[] args) {
		MyImage img = new MyImage("F:/bt_xianghui/1.png");
		System.out.println(img.getHeight() + " kuan:" + img.getWidth());
	}
}
