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
public class ImgPostAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String header = request.getHeader("Request Method");
		String content_type = request.getContentType();
		System.out.println("header:" + header);
		System.out.println("content_type:" + content_type);
		System.out.println();
		System.out.println("name:" + name + "width:" + width + "height:" + height );
		return null;
	}

}
