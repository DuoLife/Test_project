package socket;

import java.io.IOException;
import java.net.ServerSocket;

public class TalkServerSocket {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(12345);
			ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
