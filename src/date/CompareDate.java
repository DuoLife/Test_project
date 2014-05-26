package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			
			Date date = new Date();
			date.setDate(date.getDate()-1);
			//date.setDate(date.getDay()-1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			String dateStr = sdf.format(date);
			System.out.println(dateStr);
			System.out.println(1000*60*60*24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
