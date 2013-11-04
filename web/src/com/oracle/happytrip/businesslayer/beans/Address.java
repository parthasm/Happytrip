package com.oracle.happytrip.businesslayer.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Address {
	private String addr1;
	private String addr2;
	private Map<String,String> countries;
	private List<State> states;
	private List<City> cities;
	private Map<String,List<State>> mapState;
	private Map<String,List<City>> mapCity;
	private String country;
	private String state;
	private String city;
	private BigDecimal pin;
	private String otherContact;
	public Address(){
		countries=new HashMap<String, String>();
		countries.put("USA","USA");
		countries.put("India","India");
		
		State state1=new State();
		state1.setStateId("West Bengal");
		state1.setStateName("West Bengal");
		State state2=new State();
		state2.setStateId("Karnataka");
		state2.setStateName("Karnataka");
		State state3=new State();
		state3.setStateId("Madhya Pradesh");
		state3.setStateName("Madhya Pradesh");
		State state4=new State();
		state4.setStateId("Washington");
		state4.setStateName("Washington");
		State state5=new State();
		state5.setStateId("New York");
		state5.setStateName("New York");
		List<State> list1=new ArrayList<State>();
		list1.add(state5);
		list1.add(state4);
		List<State> list2=new ArrayList<State>();
		list2.add(state1);
		list2.add(state2);
		list2.add(state3);
		mapState=new HashMap<String, List<State>>();
		mapState.put("USA",list1);
		mapState.put("India",list2);
		
		City city1=new City();
		city1.setCityId("Kolkata");
		city1.setCityName("Kolkata");
		City city2=new City();
		city2.setCityId("Bangalore");
		city2.setCityName("Bangalore");
		City city3=new City();
		city3.setCityId("Bhopal");
		city3.setCityName("Bhopal");
		City city4=new City();
		city4.setCityId("Seattle");
		city4.setCityName("Seattle");
		City city5=new City();
		city5.setCityId("New York City");
		city5.setCityName("New York City");
		City city6=new City();
		city6.setCityId("Indore");
		city6.setCityName("Indore");
		List<City> listCity1=new ArrayList<City>();
		List<City> listCity2=new ArrayList<City>();
		List<City> listCity3=new ArrayList<City>();
		List<City> listCity4=new ArrayList<City>();
		List<City> listCity5=new ArrayList<City>();
		listCity1.add(city1);
		listCity2.add(city2);
		listCity3.add(city3);
		listCity3.add(city6);
		listCity4.add(city4);
		listCity5.add(city5);
		mapCity=new HashMap<String, List<City>>();
		mapCity.put("West Bengal",listCity1);
		mapCity.put("Karnataka",listCity2);
		mapCity.put("Madhya Pradesh",listCity3);
		mapCity.put("Washington",listCity4);
		mapCity.put("New York",listCity5);
		
		
	}
	
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public Map<String, String> getCountries() {
		return countries;
	}


	public void setCountries(Map<String, String> countries) {
		this.countries = countries;
	}


	public List<State> getStates() {
		return states;
	}


	public void setStates(List<State> states) {
		this.states = states;
	}


	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}


	public Map<String, List<State>> getMapState() {
		return mapState;
	}


	public void setMapState(Map<String, List<State>> mapState) {
		this.mapState = mapState;
	}


	public Map<String, List<City>> getMapCity() {
		return mapCity;
	}


	public void setMapCity(Map<String, List<City>> mapCity) {
		this.mapCity = mapCity;
	}


	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	public BigDecimal getPin() {
		return pin;
	}


	public void setPin(BigDecimal pin) {
		this.pin = pin;
	}


	public String getOtherContact() {
		return otherContact;
	}


	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}
	
	
}
