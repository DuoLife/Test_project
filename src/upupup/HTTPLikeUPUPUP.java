/**
* <p>Title: HTTPLikeUPUPUP.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-1
* @version 1.0
*/
package upupup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Title: HTTPLikeUPUPUP.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-1
 * Email: vip6ming@126.com
 */
public class HTTPLikeUPUPUP {
	
	int i =0;
	public void LikeUP(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
        con.setRequestProperty("Pragma:", "no-cache");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Content-Type", "text/xml");
		 BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         String line = "";
         for (line = br.readLine(); line != null; line = br.readLine()) {
             System.out.println(line);
         }
		
		System.out.println(i++);
	}

}
