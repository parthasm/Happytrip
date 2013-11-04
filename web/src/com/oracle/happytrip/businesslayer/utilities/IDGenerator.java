package com.oracle.happytrip.businesslayer.utilities;

public class IDGenerator 
{
	
	public static String paymentReferenceNoGenerator()
	{
		
		return "Happy"+((int)(Math.random()*100000000));
	}
	
	public static String passwordGenerator()
	{
		
		return "Happy"+((int)(Math.random()*10000.0))+"jnsbd"+((int)(Math.random()*100.0));
	}
	
	public static long idGenerator()
	{
		return (long)(Math.random()*100000000000l);
	}
	
	public static int idIntGenerator()
	{
		return (int)(Math.random()*10000000);
	}
	
	
	public static void main(String[] arg)
	{
		System.out.println(paymentReferenceNoGenerator());
		String jshn = "djhbdh.hsjdhn.adghs";
		System.out.println(jshn.lastIndexOf("."));
	}
	
	
}
