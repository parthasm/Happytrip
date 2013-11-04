package com.oracle.happytrip.businesslayer.beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote;
import com.oracle.happytrip.businesslayer.entities.Genericuser;
@ManagedBean
@SessionScoped
public class LoginBean 
{
	private String emailid,password;
	private Genericuser user;
	private ProfileBean profileBean = new ProfileBean();
	private UserDAORemote ejbRef;
	public LoginBean(){
		
		InitialContext namingService=null;
		
		try {
			
			namingService=new InitialContext();
				ejbRef=(UserDAORemote)namingService.lookup("UserDAOMapping#com.oracle.happytrip.businesslayer.ejb.utilities.UserDAORemote");
				//System.out.println(ejbRef);
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
			
		}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Genericuser getUser() {
		return user;
	}
	public void setUser(Genericuser user) {
		this.user = user;
	}
	public String forgotPassword()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		//code to send email
		context.addMessage("email", new FacesMessage("An email has been sent to your given email id"));
		return null;
	}
	public String login()
	{
	 FacesContext context = FacesContext.getCurrentInstance();
	 
	 
		if(emailid==null)
		{
			context.addMessage("email",new FacesMessage("Kindly enter your email id"));
			return null;
		}
		if(!ejbRef.emailExists(emailid))
		{
			context.addMessage("email",new FacesMessage("Sorry! Given email id does not belong to any user"));
			return null;
		}
		if(password==null)
		{
			context.addMessage("password",new FacesMessage("Kindly enter your password"));
			return null;
		}
		Genericuser u = ejbRef.getUser(emailid, password);
		if(u==null)
		{
			context.addMessage("displAtTop", new FacesMessage("Sorry! wrong username/password!"));
			return null;
		}
		profileBean.setGenUser(u);
		return "success";
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}

	public ProfileBean getProfileBean() {
		return profileBean;
	}
}
