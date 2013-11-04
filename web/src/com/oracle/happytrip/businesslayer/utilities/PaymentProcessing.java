package com.oracle.happytrip.businesslayer.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.happytrip.businesslayer.beans.NewPassenger;
import com.oracle.happytrip.businesslayer.beans.SearchBean;
import com.oracle.happytrip.businesslayer.beans.User;
import com.oracle.happytrip.businesslayer.ejb.utilities.*;
import com.oracle.happytrip.businesslayer.entities.*;

public class PaymentProcessing 
{
	private static BookingsAirline b=new BookingsAirline();
	private static Passengre passenger=new Passengre();
	private static Payment pay=new Payment();
	private static Genericuser genU = new Genericuser();
	private static BookingDAORemote ejbRef;
	static
	{
		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRef=(BookingDAORemote)namingService.lookup("BookingDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.BookingDAORemote");
			//System.out.println(ejbRef);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}

	public static boolean insertPayment(double amount)
	{

		try
		{
			pay.setPaymentid(IDGenerator.idGenerator());
			pay.setPaidamount(amount);
			pay.setPaymentdate(Calendar.getInstance().getTime());
			pay.setPaymentreferenceno(IDGenerator.paymentReferenceNoGenerator());
			ejbRef.insertPayment(pay);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean insertGenericUser(User user)
	{
		try
		{
			Set<BookingsAirline> bookingsAirlines = new HashSet<BookingsAirline>();
			genU.setUserid(IDGenerator.idIntGenerator());
			genU.setAddress(user.getAddress().getAddr1()+" "+user.getAddress().getAddr2());
			genU.setCity(user.getAddress().getCity());
			genU.setCountry(user.getAddress().getCountry());
			genU.setCreateddate(Calendar.getInstance().getTime());
			genU.setDateofbirth(user.getDob());
			genU.setEmailid(user.getEmail());
			genU.setFullname(user.getName());
			genU.setGender(user.getGender());
			genU.setMobileno(user.getMobileNo());
			genU.setOthercontactinfo(user.getAddress().getOtherContact());
			genU.setPassword(IDGenerator.passwordGenerator());
			genU.setPincode(user.getAddress().getPin());
			genU.setState(user.getAddress().getState());
			genU.setBookingsAirlines(bookingsAirlines);
			if(ejbRef.insertUnregisteredUser(genU))
				return true;
			else
			{
				return false;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	public static String insertBooking(SearchBean searchBean,List<NewPassenger> passengers)
	{
		boolean flag=false;
		try
		{
			Set<Passengre> passengres = new HashSet<Passengre>();
			Long id = IDGenerator.idGenerator();
			b.setBookingid(id);
			b.setDateOfJourney(searchBean.getDateOfJourney());
			b.setNoofseats(searchBean.getSelectedNumberOfSeats());
			b.setPayment(pay);
			if(searchBean.getSelectedFlightRoute()!=null)
			{	
				b.setFlightRoute(searchBean.getSelectedFlightRoute());
				b.setTotalcost(searchBean.getSelectedNumberOfSeats()*searchBean.getSelectedFlightRoute().getCostperticket());
			}
			else
			{
				flag=true;
				b.setFlightRoute(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0]);
				b.setTotalcost(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0].getCostperticket()*searchBean.getSelectedNumberOfSeats());
			}
				
			b.setGenericuser(genU);
			b.setPassengres(passengres);
			if(ejbRef.insertBooking(b))
			{
				if(passengers==null)
					return null;
				for(NewPassenger p:passengers)
				{
					if(!insertPassenger(p))
						return null;
				}
				if(!flag)
					return id+" for the flight";
				long id2=IDGenerator.idGenerator();
				b.setBookingid(id2);
				b.setDateOfJourney(searchBean.getDateOfJourney());
				b.setNoofseats(searchBean.getSelectedNumberOfSeats());
				b.setPayment(pay);
				
				b.setFlightRoute(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[1]);
				b.setTotalcost(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[1].getCostperticket()*searchBean.getSelectedNumberOfSeats());
			
				
				b.setGenericuser(genU);
				b.setPassengres(passengres);
				if(ejbRef.insertBooking(b))
				{
					for(NewPassenger p:passengers)
					{
						if(!insertPassenger(p))
							return null;
					}
					return id+" for 1st flight and reference no. "+id2+" for 2nd flight";
				}
			}
			return null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

	}

	public static String insertBookingLoggedIn(SearchBean searchBean,List<NewPassenger> passengers,Genericuser u)
	{
		boolean flag=false;
		try
		{
			Set<Passengre> passengres = new HashSet<Passengre>();
			
			Long id = IDGenerator.idGenerator();
			b.setBookingid(id);
			b.setDateOfJourney(searchBean.getDateOfJourney());
			b.setNoofseats(searchBean.getSelectedNumberOfSeats());
			b.setPayment(pay);
			if(searchBean.getSelectedFlightRoute()!=null)
			{	
				b.setFlightRoute(searchBean.getSelectedFlightRoute());
				b.setTotalcost(searchBean.getSelectedNumberOfSeats()*searchBean.getSelectedFlightRoute().getCostperticket());
			}
			else
			{
				flag=true;
				b.setFlightRoute(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0]);
				b.setTotalcost(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0].getCostperticket()*searchBean.getSelectedNumberOfSeats());
			}
				
			b.setGenericuser(u);
			b.setPassengres(passengres);
			if(ejbRef.insertBooking(b))
			{
				if(passengers==null)
					return null;
				for(NewPassenger p:passengers)
				{
					if(!insertPassenger(p))
						return null;
				}
				if(!flag)
					return id+" for your flight";
				long id2=IDGenerator.idGenerator();
				b.setBookingid(id2);
				b.setDateOfJourney(searchBean.getDateOfJourney());
				b.setNoofseats(searchBean.getSelectedNumberOfSeats());
				b.setPayment(pay);
				
				b.setFlightRoute(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[1]);
				b.setTotalcost(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[1].getCostperticket()*searchBean.getSelectedNumberOfSeats());
			
				
				b.setGenericuser(u);
				b.setPassengres(passengres);
				if(ejbRef.insertBooking(b))
				{
					for(NewPassenger p:passengers)
					{
						if(!insertPassenger(p))
							return null;
					}
					return id+" for 1st flight and reference no. "+id2+" for 2nd flight";
				}
			}
			return null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

	}

	
	private static boolean insertPassenger(NewPassenger p)
	{
		try
		{
			passenger.setPassengerid(IDGenerator.idGenerator());
			passenger.setBookingsAirline(b);
			passenger.setDateofbirth(p.getDob());
			passenger.setFullname(p.getName());
			passenger.setGender(p.getGender());
			
			if(ejbRef.insertPassenger(passenger))
				return true;
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}


}
