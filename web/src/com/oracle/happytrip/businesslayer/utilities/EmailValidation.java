package com.oracle.happytrip.businesslayer.utilities;



import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote;

public class EmailValidation 
{
	private static UserDAORemote ejbRef;

	public static boolean checkFormat(String email)
	{
		String[] arrAt= email.split("@");
		if(arrAt.length!=2)
			return false;
		String domain = email.substring(email.indexOf("@")+1);
		if(domain.indexOf(".")==-1 || domain.indexOf(".")==0 || domain.indexOf(".")==(domain.length()-1))
			return false;
		return true;
	}
	private static void init()
	{
		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRef=(UserDAORemote)namingService.lookup("UserDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote");
			//System.out.println(ejbRef);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}
	public static boolean checkInDatabase(String email)
	{

		init();
		if(!ejbRef.emailExists(email))
			return false;
		else
			return true;

	}

	public static void main(String[] args)
	{
		System.out.println(checkFormat("jasdb@")+"\t"+checkFormat("ad@ns")+"\t"
				+checkFormat("asdbhcc.c"));
	}
}
