package com.oracle.happytrip.businesslayer.utilities;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote;
import com.oracle.happytrip.businesslayer.entities.BookingsAirline;
import com.oracle.happytrip.businesslayer.entities.Flight;
import com.oracle.happytrip.businesslayer.entities.FlightRoute;
import com.oracle.happytrip.businesslayer.entities.RoutesAirline;


public class GetAvailability 
{
	private static FlightDAORemote ejbRef;
	
	static
	{
		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRef=(FlightDAORemote)namingService.lookup("FlightDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote");
			//System.out.println(ejbRef);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}
	public static boolean checkAvailableSeats(FlightRoute fr, Date dateOfJourney,int noOfSeats)
	{
		int number=noOfSeats;
		int sum=0;
		Flight f = fr.getFlight();
		List<FlightRoute> frs = ejbRef.getFlightRoutesfromFlight(f);
		for(FlightRoute flightRoute:frs)
		{
			List<BookingsAirline> bookings = ejbRef.getBookingsFromFlightRouteandDateOfJourney(flightRoute,dateOfJourney);
			if(bookings==null)
				continue;
			for(BookingsAirline booking:bookings)
			{
				number+=(int)(booking.getNoofseats());
				sum+=(int)(booking.getNoofseats());
			}
		}
		
		//System.out.println(number+" sum:"+sum);
		
		if(number>=f.getNoofseats())
			return false;
		
			return true;
	}
}
