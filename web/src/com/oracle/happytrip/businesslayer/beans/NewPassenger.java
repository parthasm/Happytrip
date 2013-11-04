package com.oracle.happytrip.businesslayer.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.oracle.happytrip.businesslayer.utilities.StringDateConversion;

public class NewPassenger {
	private String name;
	private Map<String,String> genders;
	private Date dob;
	private String dobString;
	private String gender;
	
	public NewPassenger(){
		genders=new HashMap<String, String>();
		genders.put("Female","F");
		genders.put("Male","M");
	}
	
	
	
	public String getDobString() {
		return dobString;
	}

	public void setDobString(String dobString) {
		this.dobString = dobString;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
		this.setDobString(StringDateConversion.dateToString(dob));
	}

	public Map<String, String> getGenders() {
		return genders;
	}

	public void setGenders(Map<String, String> genders) {
		this.genders = genders;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
