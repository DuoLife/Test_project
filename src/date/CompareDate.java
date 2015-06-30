package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;


public class CompareDate {

	public static void main(String[] args) {
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//			long nowL = System.currentTimeMillis();
////			//long l = (nowL/10000000)*10000000;
//			Date date;
//			date = sdf1.parse("2013-12-04");
//			long l = date.getTime();
//			date = new Date(l);
//			System.out.println("date : "+sdf.format(date)+"\nlong : "+nowL+"\nlong : "+l);
			
			
			
			/**************************************************/
//			long l = (System.currentTimeMillis()/10000)*10000;
//			Date date = new Date();
//			date = sdf1.parse("2013-12-04 11:11:11");
//			System.out.println(date);
//			long l = date.getTime();
//			Date date1 = new Date(l);
//			System.out.println(sdf.format(date1));
//			System.out.println(l);
//			
//			Date date = new Date();
//			System.out.println(date);
//			date.setDate(date.getDate()-1);
//			//date.setDate(date.getDay()-1);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
//			SimpleDateFormat sdatef = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH);
//			Date d = sdatef.parse((date.toString()));
//			System.out.println(d);
//			String dateStr = sdf.format(date);
//			System.out.println(dateStr);
//			System.out.println(1000*60*60*24);
			  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		        Date d1=sdf.parse("2012-10-03 10:10:10");  
		        Date d2=sdf.parse("2015-06-30 16:44:00");  
		        System.out.println(daysBetween(d1,d2)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }   
}
