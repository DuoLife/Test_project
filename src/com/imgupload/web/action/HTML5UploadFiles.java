package com.imgupload.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;


public class HTML5UploadFiles extends Action{


	private final static boolean writeToFile = true;
	private static String projectPath;
	private static String mesg;
	private static boolean success;
	private static Map result = new HashMap();
	private static String[] allowFileTypes = {"jpg", "jpeg", "png","JPG", "JPEG", "PNG"};
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		projectPath = request.getSession().getServletContext().getRealPath("/");
		String projectDir = request.getContextPath();
//		System.out.println(isMultipart);
		if(isMultipart) {
			try {
				//获取FileItem
				List<FileItem> items = setDiskFactoryAndGetFileItem(request, response);
				//从FileItem中获取表单数据，以及上传文件，并存储进Map结构中，方便获取
				Map m = processUpload(items);
				if(m == null) {
					outStream(response, result, mesg, success);
					return null;
				}
				String furl = (String) m.get("file");
				result.put("fileurl", projectDir + furl);
				//System.out.println(m);
				//success
				mesg = ExceptionMessage.Success;
			} catch (Exception e) {
				//异常终止
				//无需处理，只为终止正常操作。
				//下层已做处理，只为保留mesg中存储的为异常状况。并返回给客服端。
			}
		}else {
			mesg = ExceptionMessage.Shit;
		}
		outStream(response, result, mesg, success);
		return null;	
	}
	/**
	 * 
	 * @author xuming
	 * 
	 * @param 
	 * 
	 * @return 
	 * 
	 * @date 2014-10-28
	 */
	public List<FileItem> setDiskFactoryAndGetFileItem(HttpServletRequest request, HttpServletResponse response) {
		//create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//set factory constraints 约束条件
		int sizeOfTempDirThreshold = 4*1024;    //4KB
		String tempPath = projectPath+ "tempDir/";
		File repository = judgeDirectoryAndCreate(tempPath);
		factory.setSizeThreshold(sizeOfTempDirThreshold);
		factory.setRepository(repository);
		
		//create a new File upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		//set overall request size constraint
		long sizeMax = 40 * 1024 * 1024;    //40MB
		upload.setSizeMax(sizeMax);
		
		//parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			if(null == items.get(0).getName()) {
				throw new NULLFileItemException();
			}
			return items;
		} catch (FileUploadException e) {
			//e.printStackTrace();
			if(e instanceof SizeLimitExceededException) {
				mesg = ExceptionMessage.OverSize;
				return null;
			}
		} catch (NULLFileItemException e) {
			mesg = ExceptionMessage.NULLFileItemException;
		}
		return null;
	}
	/**
	 * 表单元素的处理方法，提取出表单字段与对应值，并按照Key-Value形式保存Map结构中。
	 * 
	 * @author xuming
	 * 
	 * @param 
	 * 
	 * @return 
	 * 
	 * @date 2014-10-28
	 */
	public Map processUpload(List<FileItem> items) {
		Map result = new HashMap();
		Iterator iter = items.iterator();
		while(iter.hasNext()) {
			try {
				FileItem item = (FileItem) iter.next();
				if(item.isFormField()) {
					//process Form field
					result = processFormField(item, result);
				}else {
					//process upload file
					result = processUploadFile(item, result);
				}
			} catch (WriteToFileException e) {
				mesg = ExceptionMessage.WriteToFileException;
				return null;
			} catch (UnsupportedEncodingException e) {
				mesg = ExceptionMessage.UnsupportedEncodingException;
				return null;
			} catch (MismatchingFileTypeException e) {
				mesg = ExceptionMessage.MismatchingFileTypeException;
				return null;
			}
		}
		return result;
	}
	/**
	 * 处理上传文件。将文件进行存储，并获取文件在服务器的路径。并将上传的表单字段作为Key，文件路径作为Value，存储进原Map
	 * 
	 * @author xuming
	 * 
	 * @param FileItem item, Map result
	 * 
	 * @return 带有文件路径的Map结构，Key为上传时的表单字段Name
	 * 
	 * @throws WriteToFileException, MismatchingFileTypeException 
	 * 
	 * @date 2014-10-28
	 */
	private Map processUploadFile(FileItem item, Map result) throws WriteToFileException, MismatchingFileTypeException {
		if(!item.isFormField()) {
			String key = item.getFieldName();
			String value = "";
			String filename = item.getName();
			//get file type
			String fileType = getFileType(filename);
			//is upload file in allow file types
			if(!isAllowFileType(fileType)) {
				throw new MismatchingFileTypeException();
			}
//			System.out.println(fileType);
//			String contentType = item.getContentType();
//			long sizeInBytes = item.getSize();
//			System.out.println("key:" + key);
//			System.out.println("value:" + value);
//			System.out.println("filename:" + filename);
//			System.out.println("contentType:" + contentType);
//			System.out.println("sizeInBytes:" + sizeInBytes/1024 + "KB");
			//write to file
			if(writeToFile) {
				try {
					String uploadFilePath = projectPath + "uploadFile/";
					File uploadDir = judgeDirectoryAndCreate(uploadFilePath);
					//具体上传文件名称应后期生成
					UUID uuid = UUID.randomUUID();
					String uuidName = uuid.toString().replace("-", "");
					String absolutePath = uploadFilePath + uuidName + "." + fileType;
					String relativePath = "/uploadFile/" + uuidName + "." + fileType;
					File uploadFile = new File(absolutePath);
					item.write(uploadFile);
					value = absolutePath;
					result.put(key, relativePath);
				} catch (Exception e) {
					throw new WriteToFileException();
				}
			}
		}
		return result;
	}
	/**
	 * @author xuming
	 * 
	 * @param 
	 * 
	 * @return 
	 * 
	 * @date 2014-10-28
	 */
	private boolean isAllowFileType(String fileType) {
		boolean isAllow = false;
		for(String s: allowFileTypes) {
			if(s.equals(fileType))
				isAllow = true;
		}
		return isAllow;
	}
	/**
	 * 处理表单元素，将表单元素按Key-Value添加进原Map结构。方便别处调用。
	 * 
	 * @author xuming
	 * 
	 * @param FileItem item, Map result
	 * 
	 * @return 将表单元素按Key-Value添加进原Map结构
	 * 
	 * @throws UnsupportedEncodingException 
	 * 
	 * @date 2014-10-28
	 */
	private Map processFormField(FileItem item, Map result) throws UnsupportedEncodingException {
		if(item.isFormField()) {
			String key = item.getFieldName();
			String value = item.getString("utf-8");
			result.put(key, value);
		}
		return result;
	}
	
	public void outStream(HttpServletResponse response,Map result, String mesg, boolean success) {
		try {
			Gson g = new Gson();
			result.put("mesg", mesg);
			result.put("success", success);
			String json = g.toJson(result);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 判断文件夹是否存在，如果不存在就创建一个新的文件夹
	 * 
	 * @author xuming
	 * 
	 * @param String filePath
	 * 
	 * @return 
	 * 
	 * @date 2014-10-28
	 */
	public File judgeDirectoryAndCreate(String filePath) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}
	/**
	 * 获取文件尾缀，也就是文件的类型
	 * @author xuming
	 * 
	 * @param String filename
	 * 
	 * @return 
	 * 
	 * @date 2014-10-28
	 */
	private String getFileType(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1);
	}
}
