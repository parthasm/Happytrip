package com.oracle.happytrip.businesslayer.beans;


import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.happytrip.businesslayer.ejb.utilities.*;
import com.oracle.happytrip.businesslayer.entities.*;
import com.oracle.happytrip.businesslayer.utilities.GetAvailability;
import com.oracle.happytrip.businesslayer.utilities.StringDateConversion;
import com.oracle.happytrip.businesslayer.utilities.ThreeMonthsAfter;



@ManagedBean
@SessionScoped
public class SearchBean 
{
	private List<com.oracle.happytrip.businesslayer.entities.City> cities;

	private FlightDAORemote ejbRef;
	private String fromCity,toCity,selectedClass;
	private Date dateOfJourney;
	private String dateOfJourneyString,dateOfBookingString;
	private Map<Integer,Integer> numberOfSeats = new HashMap<Integer, Integer>();
	private Integer selectedNumberOfSeats;
	private Map<String,String> classes = new HashMap<String, String>();
	private RoutesAirline route;
	private List<FlightRoute> flightRoutes;
	
	private Map<Long, String> flightStatusMap = new HashMap<Long, String>();

	private HtmlDataTable hdt;
	private Long selectedFlightRouteId;
	private FlightRoute selectedFlightRoute;
	
	private IndirectFlightBean indirectFlightBean = new IndirectFlightBean();
	
	public SearchBean()
	{
		InitialContext namingService = null;

		try {
			namingService=new InitialContext();
			ejbRef = (FlightDAORemote)namingService.lookup("FlightDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}

		cities = ejbRef.getCities();
		
		numberOfSeats.put(0, 0);
		numberOfSeats.put(1, 1);
		numberOfSeats.put(2, 2);
		numberOfSeats.put(3, 3);
		numberOfSeats.put(4, 4);
		numberOfSeats.put(5, 5);
		numberOfSeats.put(6, 6);
		numberOfSeats.put(7, 7);
		numberOfSeats.put(8, 8);
		numberOfSeats.put(9, 9);
		numberOfSeats.put(10, 10);
		numberOfSeats.put(11, 11);
		numberOfSeats.put(12, 12);
		classes.put("Economy", "Economy");
		classes.put("Business", "Business");
	}

	public void setSelectedFlightRouteId(Long selectedFlightRouteId) 
	{
		this.selectedFlightRouteId = selectedFlightRouteId;
		if(this.selectedFlightRouteId!=null)
			this.setSelectedFlightRoute(ejbRef.getFlightRoute(selectedFlightRouteId));
	}




	public Long getSelectedFlightRouteId() {
		return selectedFlightRouteId;
	}





	public void setDateOfBookingString(String dateOfBookingString) {
		this.dateOfBookingString = dateOfBookingString;
	}

	public String getDateOfBookingString() {
		return StringDateConversion.dateToString(Calendar.getInstance().getTime());
	}

	public List<FlightRoute> getFlightRoutes()
	{
				Collections.sort(flightRoutes, new Comparator<FlightRoute>() {
		 
					@Override
					public int compare(FlightRoute f1, FlightRoute f2)
					{
		 
						return f1.getCostperticket().compareTo(f2.getCostperticket());
		 
					}
		 
				});		  
		return flightRoutes;
	}


	public void setFlightRoutes(List<FlightRoute> flightRoutes) {
		this.flightRoutes = flightRoutes;
	}


	public RoutesAirline getRoute() {
		return route;
	}


	public void setRoute(RoutesAirline route) {
		this.route = route;

	}


	public Map<Integer, Integer> getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(Map<Integer, Integer> numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public Integer getSelectedNumberOfSeats() {
		return selectedNumberOfSeats;
	}


	public void setSelectedNumberOfSeats(Integer selectedNumberOfSeats) {
		this.selectedNumberOfSeats = selectedNumberOfSeats;
		
	}


	public Date getDateOfJourney() {
		return dateOfJourney;
	}


	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
		
	}

	public String getSelectedClass() {
		return selectedClass;
	}


	public void setSelectedClass(String selectedClass) 
	{
		this.selectedClass = selectedClass;
	}


	public Map<String, String> getClasses() {
		return classes;
	}


	public void setClasses(Map<String, String> classes) {
		this.classes = classes;
	}


	public String getFromCity() {
		return fromCity;
	}


	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}


	public String getToCity() {
		return toCity;
	}


	public void setToCity(String toCity) 
	{
		this.toCity = toCity;
	}


	public List<com.oracle.happytrip.businesslayer.entities.City> getCities() {
		return cities;
	}

	public void setCities(List<com.oracle.happytrip.businesslayer.entities.City> cities) {
		this.cities = cities;
	}

	public void setFlightStatusMap(Map<Long, String> flightStatusMap) {
		this.flightStatusMap = flightStatusMap;
	}



	public Map<Long, String> getFlightStatusMap() {
		return flightStatusMap;
	}

	public void setDateOfJourneyString(String dateOfJourneyString) {
		this.dateOfJourneyString = dateOfJourneyString;
	}

	public String getDateOfJourneyString() 
	{
		if(dateOfJourney!=null)
		return StringDateConversion.dateToString(dateOfJourney);
		return null;
	}

	public void setHdt(HtmlDataTable hdt) {
		this.hdt = hdt;
	}

	public HtmlDataTable getHdt() {
		return hdt;
	}
	
	public void setSelectedFlightRoute(FlightRoute selectedFlightRoute) {
		this.selectedFlightRoute = selectedFlightRoute;
		
	}


	public FlightRoute getSelectedFlightRoute() {
		return selectedFlightRoute;
	}



	public void setIndirectFlightBean(IndirectFlightBean indirectFlightBean) {
		this.indirectFlightBean = indirectFlightBean;
	}




	public IndirectFlightBean getIndirectFlightBean() {
		return indirectFlightBean;
	}

	public String getStatus(FlightRoute fr)
	{
		if(GetAvailability.checkAvailableSeats(fr, dateOfJourney,this.selectedNumberOfSeats))
			return "Seats Available";
		else
			return "Full";
	}
	
	
	public String searchFlights()
	{
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(this.fromCity.equals(this.toCity))
		{
			context.addMessage("tocity", new FacesMessage("Start & Destination cities cannot be the same!"));
			return null;
		}
		Date now = Calendar.getInstance().getTime();
		if(now.after(this.dateOfJourney))
		{
			context.addMessage("popupButtonCal", new FacesMessage("Sorry You cannot select a date in the past!"));
			return null;
		}
		if(!ThreeMonthsAfter.lessThanThreeMonthsAfter(dateOfJourney))
		{
			context.addMessage("popupButtonCal", new FacesMessage("Sorry! You cannot book tickets beyond 3 months of the current date"));
			return null;
		}
		this.route = ejbRef.getRouteFromTwoCities(fromCity,this.toCity);
		if(this.selectedNumberOfSeats==0)
		{
			context.addMessage("noofseats", new FacesMessage("Kindly select the correct number of seats"));
			return null;
		}
		if(this.route==null)
		{
			List<FlightRoute[]> frs = this.getIndirectFlights();
			if(this.getIndirectFlights()==null)
			{
				context.addMessage("noflight", new FacesMessage("Sorry! No Flights are available!"));
				return null;
			}
			else
			{
				indirectFlightBean.setDispFlightRoutes(frs);
				for(FlightRoute[] frArr:frs)
				{
					if(this.getStatus(frArr[0]).equals("Full") || this.getStatus(frArr[1]).equals("Full"))
						indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Full");
					else
						indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Seats Available");
				}
				return "indirect";
			}
		}
		else
		{
			this.flightRoutes = ejbRef.getFlightRoutesFromRoute(route);
			if(this.flightRoutes==null || this.flightRoutes.size()==0)
			{
				List<FlightRoute[]> frs = this.getIndirectFlights();
				if(frs==null)
				{
					context.addMessage("noflight", new FacesMessage("Sorry! No Flights are available!"));
					return null;
				}
				else
				{
					indirectFlightBean.setDispFlightRoutes(frs);
					for(FlightRoute[] frArr:frs)
					{
						if(this.getStatus(frArr[0]).equals("Full") || this.getStatus(frArr[1]).equals("Full"))
							indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Full");
						else
							indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Seats Available");
					}
					return "indirect";
				}
				
			}
			for(FlightRoute fr:this.flightRoutes)
			{
				this.flightStatusMap.put(fr.getFlightrouteid(), this.getStatus(fr));
			}
			return "success";
		}
			
		
		
	}

	
	public List<FlightRoute[]> getIndirectFlights()
	{
		return ejbRef.getIndirectRoutesFromCities(fromCity, toCity);
	}
	
	public String searchIndirectFlights()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		List<FlightRoute[]> flightRoutes = this.getIndirectFlights();
		if(flightRoutes==null)
		{
			context.addMessage("indirect", new FacesMessage("Sorry! No indirectFlights are available"));
			return null;
		}
		indirectFlightBean.setDispFlightRoutes(flightRoutes);
		for(FlightRoute[] frArr:flightRoutes)
		{
			if(this.getStatus(frArr[0]).equals("Full") || this.getStatus(frArr[1]).equals("Full"))
				indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Full");
			else
				indirectFlightBean.getFlightStatusMap().put(frArr[0].getFlightrouteid(),"Seats Available");
		}
		return "indirect";
	}


		public void selectFlight()throws IOException
	{
		try
		{
			int index = this.hdt.getRowIndex(); // Actually not interesting info.
		    this.setSelectedFlightRoute((FlightRoute)hdt.getRowData());
		 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public String proceedToBooking()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(this.selectedFlightRoute==null)
		{
			context.addMessage("msg", new FacesMessage("Kindly select a Flight"));
			return null;
		}

		if(this.getStatus(selectedFlightRoute).equals("Full"))
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Seats not available on this flight"));
			return null;
		}
		return "success";

	}
	public String proceedToIndirectBooking()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(indirectFlightBean.getSelectedFlightRoutedouble()[0]==null)
		{
			//System.out.println("inside null check");
			context.addMessage("msg", new FacesMessage("Kindly select a Flight"));
			return null;
		}
		FlightRoute[] fr = indirectFlightBean.getSelectedFlightRoutedouble();
		
		if(indirectFlightBean.getFlightStatusMap().get(fr[0].getFlightrouteid()).equals("Full"))
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Seats not available on this flight"));
			return null;
		}
		return "indirect";

	}
	
	
	


}
