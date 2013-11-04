package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the BOOKINGS_AIRLINES database table.
 * 
 */
@Entity
@Table(name="BOOKINGS_AIRLINES")
public class BookingsAirline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="BOOKINGS_AIRLINES_BOOKINGID_GENERATOR", sequenceName="S_BOOKINGS_AIRLINES")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKINGS_AIRLINES_BOOKINGID_GENERATOR")
	private long bookingid;

    @Temporal( TemporalType.DATE)
    @Column(name="BOOKINGDATE")
	private Date dateOfJourney;

	private int noofseats;

	private Double totalcost;

	//bi-directional many-to-one association to FlightRoute
    @ManyToOne
	@JoinColumn(name="FLIGHTROUTEID")
	private FlightRoute flightRoute;

	//bi-directional many-to-one association to Genericuser
    @ManyToOne
	@JoinColumn(name="USERID")
	private Genericuser genericuser;

	//bi-directional many-to-one association to Payment
    @ManyToOne
	@JoinColumn(name="PAYMENTID")
	private Payment payment;

	//bi-directional many-to-one association to Passengre
	@OneToMany(mappedBy="bookingsAirline")
	private Set<Passengre> passengres;

    public BookingsAirline() {
    }

	public long getBookingid() {
		return this.bookingid;
	}

	public void setBookingid(long bookingid) {
		this.bookingid = bookingid;
	}

	public Date getDateOfJourney() {
		return this.dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public int getNoofseats() {
		return this.noofseats;
	}

	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}

	public Double getTotalcost() {
		return this.totalcost;
	}

	public void setTotalcost(Double d) {
		this.totalcost = d;
	}

	public FlightRoute getFlightRoute() {
		return this.flightRoute;
	}

	public void setFlightRoute(FlightRoute flightRoute) {
		this.flightRoute = flightRoute;
	}
	
	public Genericuser getGenericuser() {
		return this.genericuser;
	}

	public void setGenericuser(Genericuser genericuser) {
		this.genericuser = genericuser;
	}
	
	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Set<Passengre> getPassengres() {
		return this.passengres;
	}

	public void setPassengres(Set<Passengre> passengres) {
		this.passengres = passengres;
	}
	
}