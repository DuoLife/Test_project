package gc_test;

public class GCDemo {

	public static GCDemo instance=null;
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("finalize method execute!");
		instance=this;
	}
	public static void isAlive()
	{
		if(null!=instance)
			System.out.println("I'm still alive!");
		else 
			System.out.print("oh I'm dead!");
	}
	public static void main(String[] args) {
		instance=new GCDemo();
		//第一次失去引用
		instance=null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isAlive();
		/*
		 **************************第二次失去引用 
		 */
		instance=null;
		System.gc();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isAlive();
			
	}
}
