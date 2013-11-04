package com.oracle.happytrip.businesslayer.ejb.utilities;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.oracle.happytrip.businesslayer.entities.*;

@Remote
public interface FlightDAORemote 
{
	RoutesAirline getRouteFromTwoCities(String originCity,String destinationCity);
	List<City> getCities();
	List<FlightRoute> getFlightRoutesFromRoute(RoutesAirline r);
	FlightRoute getFlightRoute(Long fid);
	List<FlightRoute[]> getIndirectRoutesFromCities(String originCity,
			String destinationCity);
	List<FlightRoute> getFlightRoutesfromFlight(Flight flight);
	List<BookingsAirline> getBookingsFromFlightRouteandDateOfJourney(FlightRoute fr,Date dateOfJourney);
}
