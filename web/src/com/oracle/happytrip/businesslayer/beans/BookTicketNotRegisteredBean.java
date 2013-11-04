package com.oracle.happytrip.businesslayer.beans;




import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;





import com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote;
import com.oracle.happytrip.businesslayer.entities.FlightRoute;
import com.oracle.happytrip.businesslayer.utilities.EmailValidation;
import com.oracle.happytrip.businesslayer.utilities.PaymentProcessing;




@ManagedBean(name="bookingBean")
@SessionScoped

public class BookTicketNotRegisteredBean
{

	private SearchBean searchBean = new SearchBean();
	private FlightDAORemote ejbRef;

	private List<State> states;
	private List<City> cities;
	private String country = "USA";
	private String state = "New York";
	private User user=new User();


	private List<NewPassenger> listUser=new ArrayList<NewPassenger>();
	private NewPassenger additionalPassenger=new NewPassenger();

	private int counter;
	private String bookRefNo;

	public BookTicketNotRegisteredBean()
	{
		counter=0;
		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRef=(FlightDAORemote)namingService.lookup("FlightDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote");
			//System.out.println(ejbRef);
		} catch (NamingException e) {

			e.printStackTrace();
		}
		states = user.getAddress().getMapState().get("USA");
		cities = new ArrayList<City>();

	}







	public String getBookRefNo() {
		return bookRefNo;
	}







	public void setBookRefNo(String bookRefNo) {
		this.bookRefNo = bookRefNo;
	}







	public List<NewPassenger> getListUser() {
		return listUser;
	}





	public void setListUser(List<NewPassenger> listUser) {
		this.listUser = listUser;
	}





	public NewPassenger getAdditionalPassenger() {
		return additionalPassenger;
	}





	public void setAdditionalPassenger(NewPassenger additionalPassenger) {
		this.additionalPassenger = additionalPassenger;
	}


	public int getCounter() {
		return counter;
	}





	public void setCounter(int counter) {
		this.counter = counter;
	}





	public List<City> getCities() {
		return cities;
	}





	public void setCities(List<City> cities) {
		this.cities = cities;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;

		this.switchCity();
	}










	public SearchBean getSearchBean() {
		return searchBean;
	}




	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}




	public String getCountry() {

		return country;
	}




	public void setCountry(String country)
	{
		this.country = country;
		switchState();
	}

	public List<State> getStates() {

		return states;
	}


	public void setStates(List<State> states) {
		this.states = states;

	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void switchState()
	{

		states = user.getAddress().getMapState().get(country);

	}
	public void switchCity()
	{
		cities = user.getAddress().getMapCity().get(state);
	}


	public String book()
	{
		FacesContext context = FacesContext.getCurrentInstance();

		if(!EmailValidation.checkFormat(user.getEmail()))
		{	
			context.addMessage("email", new FacesMessage("Correct email format:\"john@example.com.\" Kindly enter your correct email address"));
			return null;
		}
		int numberOfSeats = searchBean.getSelectedNumberOfSeats();
		if(counter==0) 
		{
			NewPassenger a=new NewPassenger();
			a.setName(user.getName());
			a.setDob(user.getDob());
			a.setGender(user.getGender());
			listUser.add(a);
			if(numberOfSeats==1)
			{
				if(searchBean.getSelectedFlightRoute()==null && searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()!=null)
					return "indirect";
				return "confirmationScreen";
			}
			counter++;
			return "nextPassenger";

		}


		else
		{
			NewPassenger p1 = new NewPassenger();
			p1.setDob(this.additionalPassenger.getDob());
			p1.setDobString(this.additionalPassenger.getDobString());
			p1.setGender(this.additionalPassenger.getGender());
			p1.setName(this.additionalPassenger.getName());
			listUser.add(p1);
		//	System.out.println(this.additionalPassenger.getName());
			for(NewPassenger p:listUser)
			{
			//	System.out.println("inside:"+p.getName());
				
			}
			if(counter>=(numberOfSeats-1))
			{


				if(searchBean.getSelectedFlightRoute()==null && searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()!=null)
					return "indirect";
				return "confirmationScreen";
			}
			else
			{
				counter++;
				return "nextPassenger";
			}
		}
	}

	public String proceedToPayment()
	{
		
		if(false)//placeholder for validation
			return null;
		int noOfSeats = searchBean.getSelectedNumberOfSeats();
		FlightRoute direct = searchBean.getSelectedFlightRoute();
		FlightRoute[] indirect = searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble();
		if(direct==null && indirect!=null)
			user.getCreditCard().setPayment(noOfSeats*(indirect[0].getCostperticket()+indirect[1].getCostperticket()));
		else
			user.getCreditCard().setPayment(noOfSeats*direct.getCostperticket());
		return "proceedToPayment";
	}

	public String pay()
	{
		boolean b=true;
		FacesContext context = FacesContext.getCurrentInstance();

		if(false)//placeholder for validation
			return null;
		if(user.getCreditCard().getMonth() <=(Calendar.getInstance().getTime().getMonth()+1) && user.getCreditCard().getYear()==(Calendar.getInstance().getTime().getYear()+1900))
		{
			context.addMessage("exp",new FacesMessage("Credit card expiry date must be after current month"));
			return null;
		}

		double amount = user.getCreditCard().getPayment();
		
		if(!PaymentProcessing.insertPayment(amount))
			return "failure";
		if(!PaymentProcessing.insertGenericUser(user))
			return "failure";
		this.bookRefNo =PaymentProcessing.insertBooking(searchBean,listUser); 
		if(this.bookRefNo==null)
			return "failure";
		
		
			return "displayConfirmation";
		
			
	}


}
