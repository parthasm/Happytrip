package com.oracle.happytrip.businesslayer.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.oracle.happytrip.businesslayer.utilities.EmailValidation;

@ManagedBean
public class ForgotPasswordBean 
{
	private String email;
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String forgotPassword()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(!EmailValidation.checkInDatabase(email))
		{
			context.addMessage("email", new FacesMessage("Sorry! Given email id does not exist in the database!"));
			return null;
		}
		//code to send email
		context.addMessage("email", new FacesMessage("An email has been sent to your given email id"));
		return null;
	}
}
