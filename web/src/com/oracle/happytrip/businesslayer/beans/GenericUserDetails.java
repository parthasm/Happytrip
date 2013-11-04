package com.oracle.happytrip.businesslayer.beans;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.oracle.happytrip.businesslayer.utilities.StringDateConversion;


public class GenericUserDetails 
{
	private String emailid;

	private String password;
	
	private String address;

	private String city;

	private String country;

    @Temporal( TemporalType.DATE)
	private Date dateofbirth;

    private String dateOfBirthString;
    
	private String fullname;

	private String gender;

	private String mobileno;

	private String othercontactinfo;

	private BigDecimal pincode;

	private String state;
	
	private CreditCard creditCard = new CreditCard();

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
		this.setDateOfBirthString(StringDateConversion.dateToString(dateofbirth));
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getOthercontactinfo() {
		return othercontactinfo;
	}

	public void setOthercontactinfo(String othercontactinfo) {
		this.othercontactinfo = othercontactinfo;
	}

	public BigDecimal getPincode() {
		return pincode;
	}

	public void setPincode(BigDecimal pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDateOfBirthString() {
		return dateOfBirthString;
	}

	public void setDateOfBirthString(String dateOfBirthString) {
		this.dateOfBirthString = dateOfBirthString;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	
	
	
}
