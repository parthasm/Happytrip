package com.oracle.happytrip.businesslayer.entities;





import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the GENERICUSERS database table.
 * 
 */
@Entity
@Table(name="GENERICUSERS")
//@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="userid",discriminatorType=DiscriminatorType.INTEGER)
public class Genericuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="GENERICUSERS_USERID_GENERATOR", sequenceName="S_GENERICUSERS")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENERICUSERS_USERID_GENERATOR")
	private int userid;

	private String emailid;

	private String password;
	
	private String address;

	private String city;

	private String country;

    @Temporal( TemporalType.DATE)
	private Date createddate;

    @Temporal( TemporalType.DATE)
	private Date dateofbirth;

	private String fullname;

	private String gender;

	private String mobileno;

	private String othercontactinfo;

	private BigDecimal pincode;

	private String state;

	//bi-directional many-to-one association to BookingsAirline
	@OneToMany(mappedBy="genericuser")
	private Set<BookingsAirline> bookingsAirlines;

	

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

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
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

	public Genericuser() {
    }

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BookingsAirline> getBookingsAirlines() {
		return this.bookingsAirlines;
	}

	public void setBookingsAirlines(Set<BookingsAirline> bookingsAirlines) {
		this.bookingsAirlines = bookingsAirlines;
	}
	
	
	
}