package myPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sample02 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		System.out.println(time);
		
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		String myDate = frmt.format(time);
		System.out.println(myDate);
		
		cal.add(Calendar.DATE, 1);
		Date tmrw = cal.getTime();
		System.out.println(tmrw);
		String tmrwDate = frmt.format(tmrw);		
		System.out.println(tmrwDate);
	}

}
