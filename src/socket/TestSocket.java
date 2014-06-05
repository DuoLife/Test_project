package socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocket {

	public static void main(String[] args) {
		try {
//			InputStream input = new FileInputStream("");
//			OutputStream output = new FileOutputStream("");
	//		Scanner s = new Scanner(System.in);
	//		System.out.println(s.next() + "console");
			Socket client = new Socket("127.0.0.1", 12345);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
