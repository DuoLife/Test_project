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

public class HttpPostTest {

	public void testPost(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream());    
            String xmlInfo = getXmlInfo();
            System.out.println("urlStr=" + urlStr);
            System.out.println("xmlInfo=" + xmlInfo);
            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
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
	 private String getXmlInfo() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<videoSend>");
	        sb.append("    <header>");
	        sb.append("        <sid>1</sid>");
	        sb.append("        <type>service</type>");
	        sb.append("    </header>");
	        sb.append("    <service name=\"videoSend\">");
	        sb.append("        <fromNum>0000021000011001</fromNum>");
	        sb.append("           <toNum>33647405</toNum>");
	        sb.append("        <videoPath>mnt/5.0.217.50/resources/80009.mov</videoPath>");
	        sb.append("        <chargeNumber>0000021000011001</chargeNumber>");
	        sb.append("    </service>");
	        sb.append("</videoSend>");
	        return sb.toString();
	    }
	public static void main(String[] args) {
//		new HttpPostTest().testPost("http://localhost:2103/leng_weixin/testRequest.do");
		new HttpPostTest().testPost("http://www.baidu.com");
	}
	
}
