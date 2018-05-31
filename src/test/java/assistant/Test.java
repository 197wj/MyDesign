package assistant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time = sdf.format(1517796975000l);  
		System.out.println(time);
		
		Calendar cl = Calendar.getInstance();
		System.out.println(cl.get(Calendar.YEAR));
		System.out.println(cl.get(Calendar.MONTH)+1);
		System.out.println(cl.get(Calendar.DAY_OF_MONTH));
		System.out.println(cl.get(Calendar.DAY_OF_WEEK));
		System.out.println(cl.get(Calendar.WEEK_OF_MONTH));
		System.out.println(cl.get(Calendar.HOUR));
		System.out.println(cl.getWeekYear());
		
		System.out.println(new Date());
		
		System.out.println(sdf.parse("2017-11-30 18:05:27").getTime());
	}
}
