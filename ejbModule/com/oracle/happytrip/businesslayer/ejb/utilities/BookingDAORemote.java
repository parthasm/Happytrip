package com.oracle.happytrip.businesslayer.ejb.utilities;
import javax.ejb.Remote;

import com.oracle.happytrip.businesslayer.entities.BookingsAirline;
import com.oracle.happytrip.businesslayer.entities.Genericuser;
import com.oracle.happytrip.businesslayer.entities.Passengre;
import com.oracle.happytrip.businesslayer.entities.Payment;

@Remote
public interface BookingDAORemote 
{
	boolean insertPassenger(Passengre p);
	boolean insertPayment(Payment p);
	boolean insertBooking(BookingsAirline b);
	boolean insertUnregisteredUser(Genericuser u);
}
