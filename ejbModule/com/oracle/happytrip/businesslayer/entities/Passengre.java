package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the PASSENGRES database table.
 * 
 */
@Entity
@Table(name="PASSENGRES")
public class Passengre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="PASSENGRES_PASSENGERID_GENERATOR", sequenceName="S_PASSENGRES")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PASSENGRES_PASSENGERID_GENERATOR")
	private long passengerid;

    @Temporal( TemporalType.DATE)
	private Date dateofbirth;

	private String fullname;

	private String gender;

	//bi-directional many-to-one association to BookingsAirline
    @ManyToOne
	@JoinColumn(name="BOOKINGID")
	private BookingsAirline bookingsAirline;

    public Passengre() {
    }

	public long getPassengerid() {
		return this.passengerid;
	}

	public void setPassengerid(long passengerid) {
		this.passengerid = passengerid;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BookingsAirline getBookingsAirline() {
		return this.bookingsAirline;
	}

	public void setBookingsAirline(BookingsAirline bookingsAirline) {
		this.bookingsAirline = bookingsAirline;
	}
	
}