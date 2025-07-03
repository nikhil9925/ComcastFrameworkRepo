package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random r = new Random();
		int randomNum = r.nextInt(5000);
		return randomNum;
	}

	public String getSytemDateYYYYDDMM() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return date;
	}
	
	
	/*
	 * public String getRequiredDateYYYYDDMM(int days) { SimpleDateFormat sim=new
	 * SimpleDateFormat("yyyy-MM-dd"); Calendar c = sim.getCalendar();
	 * c.add(Calendar.DAY_OF_MONTH, 30); String reqDate = sim.format(c.getTime());
	 * return reqDate; }
	 */
	
	 public String getRequiredDateYYYYDDMM(int days) { SimpleDateFormat sdf = new
	 SimpleDateFormat("yyyy-MM-dd"); Calendar calendar = Calendar.getInstance();
	 return sdf.format(calendar.getTime());
	 }
}
