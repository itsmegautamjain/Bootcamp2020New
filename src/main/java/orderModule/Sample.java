package orderModule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Sample {
	static String accName = "Bootcamp";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		Date dt = new Date();
		System.out.println(dt.toString());
		String today = frmt.format(dt);
		System.out.println(today);
		
		Date dtTmrw = new Date(dt.getTime()+4*(1000*60*60*24));
		String tmrw = frmt.format(dtTmrw);
		System.out.println(tmrw);
	}
	
	

}
