package com.oracle.happytrip.businesslayer.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.oracle.happytrip.businesslayer.utilities.StringDateConversion;

@ManagedBean
@SessionScoped
public class User {
	private String name;
	private String gender;
	private Map<String,String> genders;
	private Date dob;
	private String dobString;
	private String nationality;
	private Address address=new Address();
	private String mobileNo;
	private String email,password;
	private CreditCard creditCard=new CreditCard();
	
	public User(){
		genders=new HashMap<String, String>();
		genders.put("Female","F");
		genders.put("Male","M");
	}
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Map<String, String> getGenders() {
		return genders;
	}
	public void setGenders(Map<String, String> genders) {
		this.genders = genders;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
		if(dob!=null)
		this.setDobString(StringDateConversion.dateToString(dob));
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	public CreditCard getCreditCard() {
		return creditCard;
	}


	public void setDobString(String dobString) {
		this.dobString = dobString;
	}


	public String getDobString() {
		return dobString;
	}
	
	

}
