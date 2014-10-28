/**
* <p>Title: ImgPostAction.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-8-12
* @version 1.0
*/
package com.imgupload.web.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Title: ImgPostAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-8-12
 * Email: vip6ming@126.com
 */
public class ImgMultipartAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String name = request.getParameter("name");
//		String width = request.getParameter("width");
//		String height = request.getParameter("height");
//		String file = request.getParameter("file");
//		System.out.println("name:" + name + "width:" + width + "height:" + height );
//		System.out.println(file);
		Enumeration e = request.getHeaderNames();
		while (e.hasMoreElements()) {
		    String name = (String)e.nextElement();
		    String value = request.getHeader(name);
		    System.out.println(name + " = " + value);
	    }
		DiskFileUpload fu = new DiskFileUpload(); 
		InputStream in = request.getInputStream();
		byte[] b = new byte[1024*4];
		int i = 0; //每次读取的字节数
		FileOutputStream outs = new FileOutputStream("F:/test.jpg");
		StringBuffer s = new StringBuffer();
		while ( (i = in.read(b)) != -1) {
//			for(int j=0; j<i;j++) {
//				//System.out.print(b[j]);
//			}
			s.append(new String(b,0,i));
			outs.write(b, 0, i);
			outs.flush();
		}
		System.out.println(s);
		in.close();
		outs.close();
		String content_type = request.getContentType();
		System.out.println("content_type:" + content_type);
		return null;
	}
	
	public byte[] doProcess(byte[] b) {
		byte[] result = null;
		String str = new String(b);
		System.out.println(str);
		return result;
	}

}
