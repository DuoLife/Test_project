package push_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class PushTest {	
	public static void main(String args[]) throws UnknownHostException, IOException{
		PushServer server = new PushServer(4000, 1200, 1000*60);
		server.startUp();
		Socket socket = new Socket("localhost",4000);
		new DataOutputStream(socket.getOutputStream()).writeUTF("root");
		
		Timer timer = new Timer();
		timer.schedule(new PushTimer(socket), 0, 1000*60*5);
		
		
		InputStreamReader bin = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(bin);
		String message = "message";
		server.push(message);
		while(true){
			try {
				System.out.println(br.readLine());
			} catch (IOException e) {
				socket.close();
				System.exit(0);
			}
		}
	}
}

class PushTimer extends TimerTask{
	Socket socket;
	DataOutputStream out;
	BufferedWriter bw;
	public PushTimer(Socket socket){
		this.socket = socket;
		try {
			this.out = new DataOutputStream(socket.getOutputStream());
			OutputStreamWriter outr = new OutputStreamWriter(socket.getOutputStream());
			bw = new BufferedWriter(outr);
			System.out.println(bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
	}
}