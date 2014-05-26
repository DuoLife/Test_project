package ip;
import java.net.*;
public class NetInfo {
 public static void main(String[] args) {
    new NetInfo().say();
    }
 public void say() {
   try {
   InetAddress i = InetAddress.getLocalHost();
   System.out.println(i);                  //计算机名称和IP
   System.out.println(i.getHostName());    //名称
   System.out.println(i.getHostAddress()); //只获得IP
   }catch(Exception e) {
	   e.printStackTrace();
   }
 }
}