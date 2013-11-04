package com.oracle.happytrip.businesslayer.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote;
import com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote;
import com.oracle.happytrip.businesslayer.entities.FlightRoute;
import com.oracle.happytrip.businesslayer.entities.Genericuser;
import com.oracle.happytrip.businesslayer.utilities.EmailValidation;
import com.oracle.happytrip.businesslayer.utilities.PaymentProcessing;

@ManagedBean
@SessionScoped
public class ProfileBean 
{
	private Genericuser genUser;
	private GenericUserDetails user = new GenericUserDetails();
	private UserDAORemote ejbRefUser;
	
	private SearchBean searchBean = new SearchBean();
	private ChangePasswordBean changePasswordBean = new ChangePasswordBean();
	private FlightDAORemote ejbRefFlight;
	
	private List<NewPassenger> listUser=new ArrayList<NewPassenger>();
	private NewPassenger additionalPassenger=new NewPassenger();

	private int counter;
	private String bookRefNo;
	
	public ProfileBean()
	{
		InitialContext namingService=null;

		try {

			namingService=new InitialContext();
			ejbRefUser=(UserDAORemote)namingService.lookup("UserDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote");
			ejbRefFlight = (FlightDAORemote)namingService.lookup("FlightDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.FlightDAORemote");
			//System.out.println(ejbRef);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}
	
	public String getBookRefNo() {
		return bookRefNo;
	}

	public void setBookRefNo(String bookRefNo) {
		this.bookRefNo = bookRefNo;
	}

	public Genericuser getGenUser() 
	{
		return genUser;
	}

	public void setGenUser(Genericuser genUser)
	{
		this.genUser = genUser;
		user.setAddress(this.genUser.getAddress());
		user.setCity(this.genUser.getCity());
		user.setCountry(this.genUser.getCountry());
		user.setDateofbirth(this.genUser.getDateofbirth());
		user.setEmailid(this.genUser.getEmailid());
		user.setFullname(this.genUser.getFullname());
		user.setGender(this.genUser.getGender());
		user.setMobileno(this.genUser.getMobileno());
		user.setOthercontactinfo(this.genUser.getOthercontactinfo());
		user.setPassword(this.genUser.getPassword());
		user.setPincode(this.genUser.getPincode());
		user.setState(this.genUser.getState());
	}

	public void setUser(GenericUserDetails user) {
		this.user = user;
	}

	public GenericUserDetails getUser() {
		return user;
	}
	
	public SearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
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

	
	
	public ChangePasswordBean getChangePasswordBean() {
		return changePasswordBean;
	}

	public void setChangePasswordBean(ChangePasswordBean changePasswordBean) {
		this.changePasswordBean = changePasswordBean;
	}

	
	
	public String changeProfile()
	{
		genUser.setAddress(this.user.getAddress());
		genUser.setCity(this.user.getCity());
		genUser.setCountry(this.user.getCountry());
		genUser.setDateofbirth(this.user.getDateofbirth());
		genUser.setEmailid(this.user.getEmailid());
		genUser.setFullname(this.user.getFullname());
		genUser.setGender(this.user.getGender());
		genUser.setMobileno(this.user.getMobileno());
		genUser.setOthercontactinfo(this.user.getOthercontactinfo());
		genUser.setPassword(this.user.getPassword());
		genUser.setPincode(this.user.getPincode());
		genUser.setState(this.user.getState());
		ejbRefUser.updateGenericUser(genUser);
		this.setGenUser(genUser);
		return "saved";
	}
	public String doNotChangeProfile()
	{
		this.setGenUser(genUser);
		return "notSaved";
	}
	
	public boolean checkUserState()
	{
		if(this.user.getAddress()==null)
			return false;
		if(this.user.getCity()==null)
			return false;
		if(this.user.getCountry()==null)
			return false;
		if(this.user.getDateofbirth()==null)
			return false;
		if(this.user.getEmailid()==null)
			return false;
		if(this.user.getFullname()==null)
			return false;
		if(this.user.getGender()==null)
			return false;
		if(this.user.getMobileno()==null)
			return false;
		if(this.user.getOthercontactinfo()==null)
			return false;
		if(this.user.getPincode()==null)
			return false;
		if(this.user.getState()==null)
			return false;
		return true;
	}
	
	public String bookDirectLoggedIn()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(searchBean.getSelectedFlightRoute()==null)
		{
			context.addMessage("msg", new FacesMessage("Kindly select a Flight"));
			return null;
		}

		if(searchBean.getStatus(searchBean.getSelectedFlightRoute()).equals("Full"))
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Seats not available on this flight"));
			return null;
		}

		if(!this.checkUserState())
		{
			context.addMessage("msg", new FacesMessage("Kindly fill all your profile details before proceeding to booking"));
			return null;
		}
		
		int numberOfSeats = searchBean.getSelectedNumberOfSeats();
		if(counter==0) 
		{
			NewPassenger a=new NewPassenger();
			a.setName(user.getFullname());
			a.setDob(user.getDateofbirth());
			a.setGender(user.getGender());
			listUser.add(a);
			if(numberOfSeats==1)
			{
				return "confirmationScreen";
			}
			counter++;
			return "nextPassenger";

		}


		else
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Random Error in code"));
			return null;
		}
	}

	public String bookIndirectLoggedIn()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0]==null)
		{
			context.addMessage("msg", new FacesMessage("Kindly select a Flight"));
			return null;
		}

		if(searchBean.getIndirectFlightBean().getFlightStatusMap().get(searchBean.getIndirectFlightBean().getSelectedFlightRoutedouble()[0].getFlightrouteid()).equals("Full"))
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Seats not available on this flight"));
			return null;
		}

		if(!this.checkUserState())
		{
			context.addMessage("msg", new FacesMessage("Kindly fill all your profile details before proceeding to booking"));
			return null;
		}
		
		
		int numberOfSeats = searchBean.getSelectedNumberOfSeats();
		if(counter==0) 
		{
			NewPassenger a=new NewPassenger();
			a.setName(user.getFullname());
			a.setDob(user.getDateofbirth());
			a.setGender(user.getGender());
			listUser.add(a);
			if(numberOfSeats==1)
			{
				return "indirectconfirmationScreen";
			}
			counter++;
			return "nextPassenger";

		}


		else
		{
			context.addMessage("msg", new FacesMessage("Sorry ! Random Error in code"));
			return null;
		}
	}
	
	public String book()
	{
		FacesContext context = FacesContext.getCurrentInstance();

		if(counter==0)
		{
			context.addMessage("errata",new FacesMessage("Sorry ! Random Error in code"));
			return null;
		}
		int numberOfSeats = searchBean.getSelectedNumberOfSeats();
		


		
			NewPassenger p1 = new NewPassenger();
			p1.setDob(this.additionalPassenger.getDob());
			p1.setDobString(this.additionalPassenger.getDobString());
			p1.setGender(this.additionalPassenger.getGender());
			p1.setName(this.additionalPassenger.getName());
			listUser.add(p1);
			//System.out.println(this.additionalPassenger.getName());
			for(NewPassenger p:listUser)
			{
				//System.out.println("inside:"+p.getName());
				
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
		this.bookRefNo =PaymentProcessing.insertBookingLoggedIn(searchBean,listUser,genUser); 
		if(this.bookRefNo==null)
			return "failure";
		
		
			return "displayConfirmation";
		
			
	}
	
	public String changePassword()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(!changePasswordBean.getOldPassword().equals(this.user.getPassword()))
		{
			context.addMessage("OldPass",new FacesMessage("Kindly enter your correct old password"));
			return null;
		}
		if(changePasswordBean.getOldPassword().equals(changePasswordBean.getNewPassword()))
		{
			context.addMessage("NewPass",new FacesMessage("Sorry! You cannot enter your old password as the new one"));
			return null;
		}
		if(!changePasswordBean.getNewPassword().equals(changePasswordBean.getConfirmPassword()))
		{
			context.addMessage("ConfPass",new FacesMessage("Kindly enter the same new password in both the fields"));
			return null;
		}
		user.setPassword(changePasswordBean.getNewPassword());
		genUser.setPassword(user.getPassword());
		
		if(!ejbRefUser.updateGenericUser(genUser))
		{
			context.addMessage("failure",new FacesMessage("Sorry! Password could not be changed!"));
			return null;
		}
		else
		{
			context.addMessage("failure",new FacesMessage("Password changed successfully!"));
			return null;
		}
	}
	
	public String logout() 
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
        return "home";
    }
	

	public String goToSearch()
	{
		return "search";
	}
}
