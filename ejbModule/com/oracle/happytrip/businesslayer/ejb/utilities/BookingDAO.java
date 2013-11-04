package com.oracle.happytrip.businesslayer.ejb.utilities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.oracle.happytrip.businesslayer.entities.BookingsAirline;
import com.oracle.happytrip.businesslayer.entities.Genericuser;
import com.oracle.happytrip.businesslayer.entities.Passengre;
import com.oracle.happytrip.businesslayer.entities.Payment;

/**
 * Session Bean implementation class BookingDAO
 */
@Stateless(mappedName = "BookingDAOMapping")
public class BookingDAO implements BookingDAORemote 
{
	@PersistenceContext(unitName="OracleDS")
	EntityManager em;

	public BookingDAO() 
	{

	}

	@Override
	public boolean insertPassenger(Passengre p) 
	{
		try
		{
			em.persist(p);
			em.flush();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean insertPayment(Payment p)
	{
		try
		{
			em.persist(p);
			em.flush();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean insertBooking(BookingsAirline b) 
	{

		try
		{
			em.persist(b);
			em.flush();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertUnregisteredUser(Genericuser u) 
	{
		try
		{
			em.persist(u);
			em.flush();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	

}
