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
import java.util.List;
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

    		TestBean tb = new TestBean();
    		Gson g = new Gson();
    		String json = g.toJson(tb);
    		System.out.println(json);
    		
    		TestBean tbObj = g.fromJson(json, TestBean.class);
    		System.out.println(tbObj.age);
    		System.out.println(tbObj.name);
    		System.out.println(tbObj.pw);
    		
//            OutputStreamWriter out = new OutputStreamWriter(con
//                    .getOutputStream(), "UTF-8"); 
//            Map map = new HashMap();
//            map.put("key", tbObj);
//            String params = Map2Json(map);
//            System.out.println("urlStr=" + urlStr);
//            System.out.println("xmlInfo=" + params);
//            out.write(params);
//            out.flush();
//            out.close();
    		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			Map gathers = g.fromJson(sb.toString(), Map.class);
			List list = (List) gathers.get("jokes");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
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
//		new HttpPostTest().testPost("http://localhost:2103/Test_project/postReceive.do?name=xu");
		new HttpPostTest().testPost("http://lengxiaohua.com/lengxiaohuaapi/jokeToLingxiTextList");
		//new HttpPostTest().testPost("http://www.baidu.com");
	}
	
}
