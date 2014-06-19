package push_server;

import java.io.IOException;

public class ServerTest {

	public static void main(String[] args) {
        PushServer server = new PushServer(4000, 1200, 1000*60);		
        try {
			server.startUp();
			Thread.sleep(1000*10);
			server.push("message");
			System.out.println("eee");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
