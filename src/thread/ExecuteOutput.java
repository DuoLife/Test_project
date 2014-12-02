/**
* <p>Title: ExecurrentOutput.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-2
* @version 1.0
*/
package thread;

/**
 * <p>Title: ExecurrentOutput.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-2
 * Email: vip6ming@126.com
 */
public class ExecuteOutput {

	public static void main(String[] args) {
//		OutputSth out1 = new OutputSth();
//		out1.run();
//		OutputSth out2 = new OutputSth();
//		out2.run();
		try {
			Thread thread1 = new Thread(new OutputSth());
			Thread thread2 = new Thread(new OutputSth());
			thread1.start();
			//Thread.sleep(1000);
			thread2.start();
			System.out.println("finish~");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
