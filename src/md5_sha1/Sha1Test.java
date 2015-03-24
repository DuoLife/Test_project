/**
* <p>Title: Sha1Test.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2015-3-4
* @version 1.0
*/
package md5_sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title: Sha1Test.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2015-3-4
 * Email: vip6ming@126.com
 */
public class Sha1Test {

	public static String Sha () {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("sha-1");
			byte[] s = sha1.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
