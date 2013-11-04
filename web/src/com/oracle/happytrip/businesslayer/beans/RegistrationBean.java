package com.oracle.happytrip.businesslayer.beans;



import java.util.Calendar;

import java.util.HashSet;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote;
import com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote;
import com.oracle.happytrip.businesslayer.entities.BookingsAirline;
import com.oracle.happytrip.businesslayer.entities.Genericuser;

import com.oracle.happytrip.businesslayer.utilities.EmailValidation;
import com.oracle.happytrip.businesslayer.utilities.IDGenerator;
import com.oracle.happytrip.businesslayer.utilities.StringDateConversion;



@ManagedBean
@SessionScoped

public class RegistrationBean {


	private UserDAORemote ejbRef;

	private User user=null;

	public RegistrationBean(){

		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRef=(UserDAORemote)namingService.lookup("UserDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote");

		} catch (NamingException e) {

			e.printStackTrace();
		}
		user = new User();
	}




	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void switchState(ValueChangeEvent e){
		String country=((String)e.getNewValue());
		user.getAddress().setStates(user.getAddress().getMapState().get(country));
	}
	public void switchCity(ValueChangeEvent e){
		String state=((String)e.getNewValue());
		user.getAddress().setCities(user.getAddress().getMapCity().get(state));
	}
	public String register()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Genericuser r = new Genericuser();
		r.setUserid(IDGenerator.idIntGenerator());
		r.setAddress(user.getAddress().getAddr1()+"   "+user.getAddress().getAddr2());
		r.setCity(user.getAddress().getCity());
		r.setState(user.getAddress().getState());
		r.setCountry(user.getAddress().getCountry());
		r.setCreateddate(Calendar.getInstance().getTime());
		r.setDateofbirth(user.getDob());
		r.setBookingsAirlines(new HashSet<BookingsAirline>());
		if(user.getEmail()!=null)
		{	
			if(!EmailValidation.checkFormat(user.getEmail()))
			{
				context.addMessage("email", new FacesMessage("Correct email format:\"john@example.com.\" Kindly enter your correct email address"));
				return null;
			}
			if(EmailValidation.checkInDatabase(user.getEmail()))
			{
				context.addMessage("email", new FacesMessage("Sorry! You already seem to have an account in the system!"));
				return null;
			}
		}
		r.setEmailid(user.getEmail());
		r.setFullname(user.getName());
		r.setGender(user.getGender());
		r.setMobileno(user.getMobileNo());
		r.setOthercontactinfo(user.getAddress().getOtherContact());
		r.setPassword(user.getPassword());
		r.setPincode(user.getAddress().getPin());
		//r.setUserid(100);
		ejbRef.insertRegisteredUser(r);
		return "success";
	}
}

