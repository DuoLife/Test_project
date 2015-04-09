/**
* <p>Title: FreeDown.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2015-3-30
* @version 1.0
*/
package maxsum;

/**
 * <p>Title: FreeDown.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2015-3-30
 * Email: vip6ming@126.com
 */
public class FreeDown {

	private static double length = 0;
	public static void main(String[] args) {
		System.out.println(getHeight(3));
		System.out.println(length);
	}
	public static double getHeight(int n) {
		double result;
		if (n == 1) 
			result = 100;
		else
			result = getHeight(n - 1)/2;
		length += result;
		return result;
	}
}
