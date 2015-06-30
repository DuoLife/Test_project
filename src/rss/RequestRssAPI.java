/**
* <p>Title: RequestRssAPI.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2015-3-10
* @version 1.0
*/
package rss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * <p>Title: RequestRssAPI.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2015-3-10
 * Email: vip6ming@126.com
 */
public class RequestRssAPI {

	public static void main(String[] args) {
		 getRssApi("http://lengxiaohua.com/lengxiaohuaapi/gather?action=getGathers&type=text");
	}
	public static String getRssApi(String apiUrl) {
		String result = getUrlResponse(apiUrl);
//		System.out.println(result);
		Gson g = new Gson();
		Map gathers = g.fromJson(result, Map.class);
		List list = (List) gathers.get("gathers");
//		System.out.println(list.get(2));
		
		String xml = progressRssJson(list);
		writeRssFile(xml);
		return apiUrl;
	}
	public static String getUrlResponse(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String progressRssJson(List<Map> jsonList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?");
		sb.append(">\r\n<rss version=\"2.0\" xmlns:slash=\"http://purl.org/rss/1.0/modules/slash/\">\r\n");
		sb.append("<channel>");
		sb.append("<title>我们爱讲冷笑话</title>");
		sb.append("<image>");
		sb.append("<title>冷兔编辑推荐</title> ");
		sb.append("<link>http://lengxiaohua.com/gather</link> ");
		sb.append("<url>http://lengxiaohua.com/img/page/logo.gif</url> ");
		sb.append("</image>");
		sb.append("<link>http://lengxiaohua.com/gather</link> ");
		sb.append("<description>我们爱讲冷笑话精选,唯一网址:lengxiaohua.com 冷兔微信:lengtoo [lengxiaohua.cn不是俺家冷兔]</description>");
		sb.append("<lastBuildDate>");
		sb.append(jsonList.get(0).get("created"));
		sb.append("</lastBuildDate>");
		sb.append("<generator>http://lengxiaohua.com</generator>");
		sb.append("<docs>http://blogs.law.harvard.edu/tech/rss</docs> ");
		sb.append("<managingEditor>lengtoo</managingEditor> ");
		sb.append("<webMaster>lengtoo</webMaster> ");
		sb.append("<language>zh-CN</language>");
		sb.append("<copyright>lengxiaohua.com All Rights Reserved.</copyright> ");
		//头部信息完毕
		//内容填充
		for(int i=0; i<10; i++) {
			Double d = (Double) jsonList.get(i).get("Gatherid");
			int gatherid = d.intValue();
			String content = getContent("http://lengxiaohua.com/lengxiaohuaapi/gather?action=getOneGather&Gatherid=" + jsonList.get(i).get("Gatherid"));
			sb.append("<item>");
			sb.append("<title>");
			sb.append(jsonList.get(i).get("title"));
			sb.append("</title>\r\n");
			sb.append("<link>"); 
			sb.append("http://lengxiaohua.com/gather/" + gatherid);
			sb.append("</link>\r\n");
			sb.append("<description>");
			sb.append("<![CDATA[<a href='http://lengxiaohua.com/gather/" + gatherid + "'><img src='http://lengxiaohua.com" + jsonList.get(i).get("uri") + "' width='150px' height='150px' border='0'/></a><br>" + content + "]]>");
			sb.append("</description>\r\n");
			sb.append("<author>冷兔</author>\r\n");
			sb.append("<source>lengxiaohua.com</source>\r\n");
			sb.append("<thumbimg>http://lengxiaohua.com" + jsonList.get(i).get("uri") + "</thumbimg>\r\n");
			sb.append("<category>编辑推荐</category>\r\n"); 
			sb.append("<comments>http://lengxiaohua.com/gather/" + gatherid + "</comments>");
			sb.append("<pubDate>" + jsonList.get(i).get("created") + "</pubDate>\r\n");
			sb.append("<guid>");
			sb.append("http://lengxiaohua.com/gather/" + gatherid);
			sb.append("</guid>\r\n");
			sb.append("</item>\r\n");
		} 
		sb.append("</channel>");
		sb.append("</rss>");
		return sb.toString();
	}
	public static String getContent(String apiUrl) {
		String result = getUrlResponse(apiUrl);
		Gson g = new Gson();
		Map gathers = g.fromJson(result, Map.class);
		String content = (String) gathers.get("content");
		content = content.replaceAll("\\[image_", "<img src='http://lengxiaohua.com/img/user/userupload/0/");
		content = content.replaceAll("\\]", ".jpg'/>");
		content = content.replaceAll("\r\n", "<br/>");
		System.out.println(content);
		return content;
	}
	public static void writeRssFile(String xml) {
		try {
			File file = new File("F:/rss.xml");
			FileOutputStream fout = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
//			byte[] b = xml.getBytes();
			writer.write(xml);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
