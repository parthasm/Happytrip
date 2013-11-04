package com.oracle.happytrip.businesslayer.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.IOException;
import java.util.*;

import com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote;
import com.oracle.happytrip.businesslayer.entities.FlightRoute;
import com.oracle.happytrip.businesslayer.utilities.GetAvailability;

@ManagedBean
public class IndirectFlightBean 
{
	private FlightDAORemote ejbRef;

	private HtmlDataTable indiHdt;

	private List<FlightRoute[]> dispFlightRoutes = new ArrayList<FlightRoute[]>();

	private Map<Long,String> flightStatusMap = new HashMap<Long, String>();

	private FlightRoute[] selectedFlightRoutedouble = new FlightRoute[2];
	private Double costPerPerson=0.0;
	
	

	public Double getCostPerPerson() {
		return costPerPerson;
	}

	public void setCostPerPerson(Double cost) {
		this.costPerPerson = cost;
	}

	public IndirectFlightBean()
	{
		InitialContext namingService = null;

		try {
			namingService=new InitialContext();
			ejbRef = (FlightDAORemote)namingService.lookup("FlightDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote");
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}

	public HtmlDataTable getIndiHdt() {
		return indiHdt;
	}

	public void setIndiHdt(HtmlDataTable indiHdt) {
		this.indiHdt = indiHdt;
	}

	public List<FlightRoute[]> getDispFlightRoutes() 
	{
		Collections.sort(dispFlightRoutes, new Comparator<FlightRoute[]>() {
			 
			@Override
			public int compare(FlightRoute[] f1, FlightRoute[] f2)
			{
				Double cost1 = f1[0].getCostperticket()+f1[1].getCostperticket();
				Double cost2 = f2[0].getCostperticket()+f2[1].getCostperticket();
 
				return cost1.compareTo(cost2);
 
			}
 
		});	
		return dispFlightRoutes;
	}

	public void setDispFlightRoutes(List<FlightRoute[]> dispFlightRoutes) 
	{
		this.dispFlightRoutes = dispFlightRoutes;
	}

	public void setFlightStatusMap(Map<Long, String> flightStatusMap) {
		this.flightStatusMap = flightStatusMap;
	}

	public Map<Long, String> getFlightStatusMap() {
		return flightStatusMap;
	}

	public void setSelectedFlightRoutedouble(FlightRoute[] selectedFlightRoutedouble) {
		this.selectedFlightRoutedouble = selectedFlightRoutedouble;
		this.setCostPerPerson(selectedFlightRoutedouble[0].getCostperticket()+selectedFlightRoutedouble[1].getCostperticket());
	}

	public FlightRoute[] getSelectedFlightRoutedouble() {
		return selectedFlightRoutedouble;
	}


	public void selectIndirectFlight()throws IOException
	{
		try
		{
			int index = this.indiHdt.getRowIndex(); // Actually not interesting info.

			this.setSelectedFlightRoutedouble((FlightRoute[])this.indiHdt.getRowData());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	

}
