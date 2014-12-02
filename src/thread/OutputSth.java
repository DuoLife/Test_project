/**
* <p>Title: OutputSth.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-2
* @version 1.0
*/
package thread;

/**
 * <p>Title: OutputSth.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-2
 * Email: vip6ming@126.com
 */
public class OutputSth implements Runnable{

	private static int countTask = 0;
	private final int id = countTask++;
	int outputTimes = 10;
	public OutputSth() {};
	public OutputSth(int outputTimes) {
		this.outputTimes = outputTimes;
	};
	
	public void run() {
		int n = 0;
		while(n < outputTimes) {
			System.out.println(" #" + id + " 任务正在进行第‘" + (n++) + "’遍~");
			Thread.yield();
		}
	}

}
