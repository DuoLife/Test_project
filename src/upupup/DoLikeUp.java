/**
* <p>Title: DoLikeUp.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-1
* @version 1.0
*/
package upupup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>Title: DoLikeUp.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-1
 * Email: vip6ming@126.com
 */
public class DoLikeUp {

	public static void main(String[] args) {
		try {
			HTTPLikeUPUPUP likeup = new HTTPLikeUPUPUP();
//			String urlStr = "http://www.kanzhun.com/news/newsuseful.json?newsId=7896&_=1417426062664";
			String urlStr = "http://m.kanzhun.com/news/toutiao/7903.html";
//			String urlStr = "http://192.168.1.110:2103/lengtooyinxiang";
			URL url = new URL(urlStr);
			int count = 5000;
			for(int i=0; i<count ; i++) {
				likeup.LikeUP(url);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
