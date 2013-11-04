package com.oracle.happytrip.businesslayer.utilities;

import java.util.Calendar;
import java.util.Date;

public class ThreeMonthsAfter 
{
	public static boolean lessThanThreeMonthsAfter(Date dateofJourney)
	{
		Date now = Calendar.getInstance().getTime();
		long nowMS = now.getTime();
		long dojMS = dateofJourney.getTime();
		long diff = dojMS - nowMS;
		long diffDays = diff/(1000*60*60*24);
		if(diffDays<91l)
			return true;
		else
			return false;
		
		
	}
}
