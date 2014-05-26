package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import http.request.HttpPostTest;

/**
 * @author Rollen-Holt 继承Thread类，不能资源共享
 * */
class Hello extends Thread {
	private static int count = 50;
    public void run() {
//        for (int i = 0; i < 20; i++) {
//        	System.out.println(Hello.currentThread().getName()+"的  第"+i+"次");
////        	try {
////				Hello.sleep(1000);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//            if (count > 0) {
//                System.out.println("thread Name:"+Hello.currentThread().getName()+" count= " + count--);
//            }
//           // System.out.println("****************************************");
//        }
    	
    	
    	try {
            URL url = new URL("http://localhost:2103/leng_weixin/testRequest.do");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream());    
            String xmlInfo = "this message from client...";
            System.out.println("urlStr=" + "http://localhost:2103/leng_weixin/testRequest.do");
            String date = (new Date().toString());
            System.out.println("date  xmlInfo=" + xmlInfo);
            out.write(new String(date + xmlInfo.getBytes("ISO-8859-1")));
            out.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
            		.getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
            	System.out.println(line);
            }
			Hello.sleep(10000);
            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
            out.flush();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
 
    public static void main(String[] args) {
    	Hello h1 = new Hello();
    	h1.setName("thread1");
//    	Hello h2 = new Hello();
//    	h2.setName("thread2");
//    	Hello h3 = new Hello();
//    	h3.setName("thread3");
//    	h3.start();
//    	h2.start();
        h1.start();
        h1.setName("new thread 1");
    }
//    public static void main(String[] args) {
//        Hello he = new Hello();
//        System.currentTimeMillis();
//        Thread demo = new Hello();//new Thread(he,"线程");
//        demo.start();
//        for(int i=0;i<50;++i){
////            if(i>10){
////                try{
////                    demo.join();  //强制执行demo
////                }catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
//            System.out.println("main 线程执行-->"+i);
//        }
//    }
    
//    public static void main(String[] args) {
//    	Hello he = new Hello();
//        Thread demo = new Thread(he, "线程");
//        demo.start();
//    }
 
}