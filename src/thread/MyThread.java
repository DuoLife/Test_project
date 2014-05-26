package thread;

class MyThread implements Runnable{
	 
    private int ticket = 50;  //5张票
 
    public void run() {
        for (int i=0; i<=20; i++) {
        	System.out.println(Hello.currentThread().getName()+"的  第"+i+"次");
        	try {
				//Hello.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+ "正在卖票"+this.ticket--);
            }
            System.out.println("****************************************");
        }
    }
     
    public static void main(String [] args) {
        MyThread my = new MyThread();
        new Thread(my, "1号窗口").run();
        new Thread(my, "2号窗口").start();
        new Thread(my, "3号窗口").start();
    }
}
