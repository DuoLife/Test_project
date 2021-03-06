/**
* <p>Title: ReceivePostAction.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-8
* @version 1.0
*/
package http.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.sun.imageio.plugins.common.InputStreamAdapter;

/**
 * <p>Title: ReceivePostAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-8
 * Email: vip6ming@126.com
 */
public class ReceivePostAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		System.out.println("server : " + request.getRequestURI());
		System.out.println(request.getParameter("name"));
		String line = "";
		for(line = bf.readLine(); line != null; line = bf.readLine()) {
			sb.append(line);
		}
		Gson g = new Gson();
		System.out.println("json : "+sb.toString());
		Map m = g.fromJson(sb.toString(), Map.class);
		System.out.println("key : " + m.get("key"));
		Map mjson = (Map) m.get("key");
		System.out.println(mjson.get("age"));
		
		TestBean tb = g.fromJson(mjson.toString(), TestBean.class);
		System.out.println(tb.age);
		return null;
	}

}
