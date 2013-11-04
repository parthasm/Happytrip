package com.oracle.happytrip.businesslayer.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.faces.bean.ManagedBean;
@ManagedBean
public class CreditCard {
	private String cardNo1,cardNo2,cardNo3,cardNo4;
	private String name;
	private Integer month;
	private int year;
	private List<Integer> months;
	private List<Integer> years;
	private String type;
	private Map<String,String> map;
	private String cvv;
	private Double payment;
	
	
	public CreditCard(){
		months=new ArrayList<Integer>();
		months.add(1);
		months.add(2);
		months.add(3);
		months.add(4);
		months.add(5);
		months.add(6);
		months.add(7);
		months.add(8);
		months.add(9);
		months.add(10);
		months.add(11);
		months.add(12);
		years=new ArrayList<Integer>();
		for(@SuppressWarnings("deprecation")
		int i=Calendar.getInstance().getTime().getYear()+1900;i<2050;i++){
			years.add(i);
		}
		map=new HashMap<String, String>();
		map.put("VISA", "VISA");
		map.put("Master Card", "Master Card");
	}
	
	public String getCardNo1() {
		return cardNo1;
	}

	public void setCardNo1(String cardNo1) {
		this.cardNo1 = cardNo1;
	}

	public String getCardNo2() {
		return cardNo2;
	}

	public void setCardNo2(String cardNo2) {
		this.cardNo2 = cardNo2;
	}

	public String getCardNo3() {
		return cardNo3;
	}

	public void setCardNo3(String cardNo3) {
		this.cardNo3 = cardNo3;
	}

	public String getCardNo4() {
		return cardNo4;
	}

	public void setCardNo4(String cardNo4) {
		this.cardNo4 = cardNo4;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getMonths() {
		return months;
	}
	public void setMonths(List<Integer> months) {
		this.months = months;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	

}
