package http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class HttpPostTest {

	public void testPost(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream()); 
            Map map = new HashMap();
            map.put("key", "中文");
            String params = Map2Json(map);
            System.out.println("urlStr=" + urlStr);
            System.out.println("xmlInfo=" + params);
            out.write(new String(params.getBytes("UTF-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	 }
	 private String Map2Json(Map map) {
	       Gson g = new Gson();
	       String json = g.toJson(map);
	       return json;
	    }
	public static void main(String[] args) {
		new HttpPostTest().testPost("http://localhost:2103/Test_project/postReceive.do?name=xu");
		//new HttpPostTest().testPost("http://www.baidu.com");
	}
	
}
