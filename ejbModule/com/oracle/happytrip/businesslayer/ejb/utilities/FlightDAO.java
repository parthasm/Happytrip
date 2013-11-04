package com.oracle.happytrip.businesslayer.ejb.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.oracle.happytrip.businesslayer.entities.City;

import com.oracle.happytrip.businesslayer.entities.BookingsAirline;
import com.oracle.happytrip.businesslayer.entities.Flight;
import com.oracle.happytrip.businesslayer.entities.FlightRoute;
import com.oracle.happytrip.businesslayer.entities.Genericuser;

import com.oracle.happytrip.businesslayer.entities.RoutesAirline;




@Stateless(mappedName = "FlightDAOMapping")
public class FlightDAO implements FlightDAORemote 
{
	@PersistenceContext(unitName="OracleDS")
	EntityManager em;

	public FlightDAO() 
	{

	}

	@Override
	public RoutesAirline getRouteFromTwoCities(String originCity,
			String destinationCity) throws NoResultException
			{

		Query q = em.createQuery("select r from RoutesAirline r where r.city2.cityname=:a and r.city1.cityname=:b");
		q.setParameter("a",originCity);
		q.setParameter("b",destinationCity);
		try
		{
			return (RoutesAirline)q.getSingleResult();
		}
		catch (Exception e) 
		{
			return null;
		}

			}

	@Override
	public List<City> getCities()
	{
		List<City> cities = (List<City>)em.createQuery("select c from City c").getResultList();
		return cities;
	}

	public List<FlightRoute> getFlightRoutesFromRoute(RoutesAirline r)
	{
		List<FlightRoute> frs= (List<FlightRoute>)em.createQuery("select fr from FlightRoute fr where fr.routesAirline.routeid="+r.getRouteid()).getResultList();
		return frs;
	}



	

	@Override
	public FlightRoute getFlightRoute(Long fid)
	{
		FlightRoute f = em.find(FlightRoute.class, fid);
		return f;
	}

	@Override
	public List<FlightRoute[]> getIndirectRoutesFromCities(
			String originCity, String destinationCity) 
	{
		List<FlightRoute[]> frs = new ArrayList<FlightRoute[]>();
		//List<RoutesAirline[]> routes = new ArrayList<RoutesAirline[]>();
		Query q1 = em.createQuery("select r from RoutesAirline r where r.city2.cityname=:a");
		q1.setParameter("a", originCity);
		Query q2 = em.createQuery("select r from RoutesAirline r where r.city1.cityname=:b");
		q2.setParameter("b", destinationCity);
		try
		{
		List<RoutesAirline> rs1 = (List<RoutesAirline>)q1.getResultList();
		List<RoutesAirline> rs2 = (List<RoutesAirline>)q2.getResultList();
			for(RoutesAirline r1:rs1)
			{
				for(RoutesAirline r2:rs2)
				{
					if(r1.getCity1().getCityname().equals(r2.getCity2().getCityname()))
					{
						List<FlightRoute> fr1 = this.getFlightRoutesFromRoute(r1);
						List<FlightRoute> fr2 = this.getFlightRoutesFromRoute(r2);
						for(FlightRoute f1:fr1)
						{
							for(FlightRoute f2:fr2)
							{
								FlightRoute[] arr= new FlightRoute[2];
								arr[0] = f1;
								arr[1] = f2;
								frs.add(arr);
							}
						}
					}
				}
			}
			
			
			if(frs.size()==0)
				return null;
			
			return frs;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<FlightRoute> getFlightRoutesfromFlight(Flight flight) 
	{
		try
		{
			List<FlightRoute> frs= (List<FlightRoute>)em.createQuery("select fr from FlightRoute fr where fr.flight.flightid="+flight.getFlightid()).getResultList();
			return frs;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<BookingsAirline> getBookingsFromFlightRouteandDateOfJourney(FlightRoute fr,Date dateOfJourney) 
	{
		try
		{
			Query q  = em.createQuery("select bk from BookingsAirline bk where bk.flightRoute.flightrouteid="+fr.getFlightrouteid()+" and bk.dateOfJourney=:a");
			q.setParameter("a", dateOfJourney);
			List<BookingsAirline> bks = (List<BookingsAirline>)q.getResultList(); 
			return bks;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	
}
