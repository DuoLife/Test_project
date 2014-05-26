//package getJava_md5_sha1;
//
//import java.io.IOException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//import com.google.gson.Gson;
//
//public class Login_test extends Action{
//
//	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
//		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		Map result = new HashMap();
//		String signature = request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		String nonce = request.getParameter("nonce");
//		String token = "E10ADC3949BA59ABBE56E057F20F883E";
//		String echostr = request.getParameter("echostr");
//		if(checkSignature(signature, timestamp, nonce, token)) {
//			result.put("echostr", echostr);
//			processjson(response, echostr);
//			System.out.println("success");
//			return null;
//		}
//		System.out.println("fail");
//		return null;
//	}
//	private boolean checkSignature(String signature, String timestamp, String nonce, String token) {
//		boolean result = false;
//		String[] strArr = new String[]{token, timestamp, nonce};
//		System.out.println("pai xu  qian "+strArr[0]+strArr[1]+strArr[2]);
//		Arrays.sort(strArr);
//		System.out.println("pai xu  hou "+strArr[0]+strArr[1]+strArr[2]);
////		String key = SHA1("");
//		String keyStr = strArr[0]+strArr[1]+strArr[2];
//		try {
//			//*******************************************//
//			MessageDigest md = MessageDigest.getInstance("SHA1");
//			md.update(keyStr.getBytes());
//			System.out.println(signature);
//			keyStr = getFormattedText(md.digest());
//			System.err.println(keyStr);
//			if(signature.equals(keyStr)) {
//				result = true;
//			}
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		System.out.println(result);
//		return result;
//	}
//	
//	public static void processjson(HttpServletResponse response, String echostr) {
//		Gson g = new Gson();
////		String json = g.toJson();
//		//System.out.println(json);
//		try {
//			//return data
//			response.setContentType("text/json; charset=utf-8");
//			response.getWriter().print(echostr);
//			response.getWriter().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	 private static String getFormattedText(byte[] bytes) {
//         int len = bytes.length;
//         StringBuilder buf = new StringBuilder(len * 2);
//         // 把密文转换成十六进制的字符串形式
//         for (int j = 0; j < len; j++) {          
//        	 buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
//             buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
//         }
//         return buf.toString();
//     }
//
//}
