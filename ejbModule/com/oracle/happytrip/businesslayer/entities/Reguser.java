//package com.oracle.happytrip.businesslayer.entities;
//
//import java.io.Serializable;
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Date;
//
//
///**
// * The persistent class for the REGUSERS database table.
// * 
// */
////s@Entity
//@Table(name="REGUSERS")
//public class Reguser extends Genericuser implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	
//
//	private String address;
//
//	private String city;
//
//
//    @Temporal( TemporalType.DATE)
//	private Date createddate;
//
//    @Temporal( TemporalType.DATE)
//	private Date dateofbirth;
//
//	private String fullname;
//
//	private String gender;
//
//	private String mobileno;
//
//	private String othercontactinfo;
//
//	private BigDecimal pincode;
//
//	private String state;
//
//	//bi-directional one-to-one association to Genericuser
//	@OneToOne
//	@JoinColumn(name="USERID")
//	private Genericuser genericuser;
//
//    public Reguser() {
//    }
//
//	
//
//	public String getAddress() {
//		return this.address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getCity() {
//		return this.city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getCountry() {
//		return this.country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public Date getCreateddate() {
//		return this.createddate;
//	}
//
//	public void setCreateddate(Date createddate) {
//		this.createddate = createddate;
//	}
//
//	public Date getDateofbirth() {
//		return this.dateofbirth;
//	}
//
//	public void setDateofbirth(Date dateofbirth) {
//		this.dateofbirth = dateofbirth;
//	}
//
//	public String getFullname() {
//		return this.fullname;
//	}
//
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//
//	public String getGender() {
//		return this.gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getMobileno() {
//		return this.mobileno;
//	}
//
//	public void setMobileno(String mobileno) {
//		this.mobileno = mobileno;
//	}
//
//	public String getOthercontactinfo() {
//		return this.othercontactinfo;
//	}
//
//	public void setOthercontactinfo(String othercontactinfo) {
//		this.othercontactinfo = othercontactinfo;
//	}
//
//	public BigDecimal getPincode() {
//		return this.pincode;
//	}
//
//	public void setPincode(BigDecimal pincode) {
//		this.pincode = pincode;
//	}
//
//	public String getState() {
//		return this.state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//
//
//	public void setGenericuser(Genericuser genericuser) {
//		this.genericuser = genericuser;
//	}
//
//
//
//	public Genericuser getGenericuser() {
//		return genericuser;
//	}
//
//	
//	
//}