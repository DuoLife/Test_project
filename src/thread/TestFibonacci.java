/**
* <p>Title: TestFibonacci.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-2
* @version 1.0
*/
package thread;

/**
 * <p>Title: TestFibonacci.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-2
 * Email: vip6ming@126.com
 */
public class TestFibonacci {

	public static void main(String[] args) {
//		Thread thread1 = new Thread(new Fibonacci(5), "ganta");
//		Thread thread2 = new Thread(new Fibonacci(5), "gayp");
//		thread1.start();
//		thread2.start();
		int length = 100;
		for(int i=0; i<length; i++) {
			Thread t = new Thread(new Fibonacci(5), "ganta");
			t.start();
		}
	}
}
