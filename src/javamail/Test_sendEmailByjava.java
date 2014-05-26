package javamail;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Test_sendEmailByjava {
	public static void main(String[] args){
        //这个类主要是设置邮件
	  MailSenderInfo mailInfo = new MailSenderInfo(); 
	  mailInfo.setMailServerHost("smtp.exmail.qq.com"); 
	  mailInfo.setMailServerPort("25"); 
	  mailInfo.setValidate(true); 
	  mailInfo.setUserName("noreply@lengtucao.com"); 
	  mailInfo.setPassword("huang@2103");//您的邮箱密码 
	  mailInfo.setFromAddress("noreply@lengtucao.com"); 
	  mailInfo.setToAddress("yi@coldworks.com");
//	  mailInfo.setToAddress("vip6ming@126.com");
	  Map l= new HashMap();
	  Integer a =0;
	  mailInfo.setSubject("密码重置");
	  String username = "易水寒";
	 mailInfo.setContent("尊敬的 "+username+" 先生/女士：<br/>&nbsp;&nbsp;&nbsp;&nbsp;你使用了本站提供的密码找回功能，如果你确认此密码找回功能是你启用的，请点击下面的链接，按流程进行密码重设。欢迎你经常访问本站。站长无喱头谢谢你经常光顾本站！<br/><a href='http://lengxiaohua.com'>我们爱讲冷笑话</a>"); 
         //这个类主要来发送邮件
	  SimpleMailSender sms = new SimpleMailSender();
        // sms.sendTextMail(mailInfo);//发送文体格式 
        sms.sendHtmlMail(mailInfo);//发送html格式
	}
	public static boolean send(String email) {
		boolean result = false;
		try {
			//这个类主要是设置邮件
			  MailSenderInfo mailInfo = new MailSenderInfo(); 
			  mailInfo.setMailServerHost("smtp.126.com"); 
			  mailInfo.setMailServerPort("25"); 
			  mailInfo.setValidate(true); 
			  mailInfo.setUserName("vip6ming@126.com"); 
			  mailInfo.setPassword("xuming2009+");//您的邮箱密码 
			  mailInfo.setFromAddress("noreply@lengtucao.com"); 
			  mailInfo.setToAddress(email); 
			  mailInfo.setSubject("密码重置"); 
			  mailInfo.setContent("您的新密码为****"); 
			    //这个类主要来发送邮件
			  SimpleMailSender sms = new SimpleMailSender();
			     //sms.sendTextMail(mailInfo);//发送文体格式 
			    sms.sendHtmlMail(mailInfo);//发送html格式
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
