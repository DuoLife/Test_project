/**
* <p>Title: LargestSubsegmentSum1.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2015-3-27
* @version 1.0
*/
package maxsum;

import java.util.Scanner;

/**
 * <p>Title: LargestSubsegmentSum1.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2015-3-27
 * Email: vip6ming@126.com
 */
public class LargestSubsegmentSum2 {
	
	public static void main(String[] args) {  
		int[] v = {1,15,-111,23,111,1239,-900,1,53,-223,123,553,-24};
		System.out.println(L(v));
	}
	public static int L (int[] arr) {
		int[] b = new int[arr.length];
//		System.out.println(b[0]);
		int sum = 0;
		for (int i=1; i<arr.length; i++) {
			if(b[i-1] > 0)
				b[i] = b[i-1] + arr[i];
			else 
				b[i] = arr[i];
			if(b[i-1] > sum) {
				sum = b[i-1];
			}
		}
		return sum;
	}
}
