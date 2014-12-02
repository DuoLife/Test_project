/**
* <p>Title: Fibonacci.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-2
* @version 1.0
*/
package thread;

/**
 * <p>Title: Fibonacci.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-2
 * Email: vip6ming@126.com
 */
public class Fibonacci implements Runnable{

	private int count = 0;
	private int begin = 0;
	private int next = 1;
	
	public Fibonacci() {};
	public Fibonacci(int count) {
		this.count = count;
	};
	
	public void run() {
		int n = 0;
		while(n <= count) {
			System.out.println("#" + Thread.currentThread().getId() + " 的线程内容为：" + begin);
			System.out.println(begin);
			int temp = begin;
			begin = next;
			next = temp + next;
			n ++;
			Thread.yield();
		}
		System.out.println("*******#" + Thread.currentThread().getId() + " 的线程结束*******");
	}
}
