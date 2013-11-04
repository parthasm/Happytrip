package com.oracle.happytrip.businesslayer.utilities;

import java.util.Date;

public class StringDateConversion 
{
	public static Date stringToDate(String date)
	{
		String[] arr = date.split("-");
		
		int year = Integer.parseInt(arr[2]);
		int month=-1;
		if(arr[1].equalsIgnoreCase("Jan"))
			month=0;
		else if (arr[1].equalsIgnoreCase("Feb")) 
		{
			month=1;
		}
		else if (arr[1].equalsIgnoreCase("Mar")) 
		{
			month=2;
		}
		else if (arr[1].equalsIgnoreCase("Apr")) 
		{
			month=3;
		}
		else if (arr[1].equalsIgnoreCase("May")) 
		{
			month=4;
		}
		else if (arr[1].equalsIgnoreCase("Jun")) 
		{
			month=5;
		}
		else if (arr[1].equalsIgnoreCase("July")) 
		{
			month=6;
		}
		else if (arr[1].equalsIgnoreCase("Aug")) 
		{
			month=7;
		}
		else if (arr[1].equalsIgnoreCase("Sep")) 
		{
			month=8;
		}
		else if (arr[1].equalsIgnoreCase("Oct")) 
		{
			month=9;
		}
		else if (arr[1].equalsIgnoreCase("Nov")) 
		{
			month=10;
		}
		else if (arr[1].equalsIgnoreCase("Dec")) 
		{
			month=11;
		}
		int day=Integer.parseInt(arr[0]);;
		@Deprecated
		Date d = new Date(year,month,day);
		return d;
	}
	public static String dateToString(Date date)
	{
		if(date==null)
			return null;
		int day = date.getDate();
		String month="";
		int monthNo = date.getMonth();
		switch (monthNo)
		{
			case 0:
				month="Jan";
			break;
			
			case 1:
				month="Feb";
			break;
			
			case 2:
				month="Mar";
			break;
			
			case 3:
				month="Apr";
			break;
			
			case 4:
				month="May";
			break;
			
			case 5:
				month="Jun";
			break;
			
			case 6:
				month="Jul";
			break;
			
			case 7:
				month="Aug";
			break;
			
			case 8:
				month="Sep";
			break;
			
			case 9:
				month="Oct";
			break;
			
			case 10:
				month="Nov";
			break;
			
			case 11:
				month="Dec";
			break;	
		default:
			break;
		}
		int year = date.getYear()+1900;
		
		return month+"/"+day+"/"+year;
		
	}
}
