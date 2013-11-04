package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the FLIGHT_ROUTES database table.
 * 
 */
@Entity
@Table(name="FLIGHT_ROUTES")
public class FlightRoute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FLIGHT_ROUTES_FLIGHTROUTEID_GENERATOR", sequenceName="S_FLIGHT_ROUTES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FLIGHT_ROUTES_FLIGHTROUTEID_GENERATOR")
	private long flightrouteid;

	private String arrivaltime;

	private Double costperticket;

	private String departuretime;

	//bi-directional many-to-one association to BookingsAirline
	@OneToMany(mappedBy="flightRoute")
	private Set<BookingsAirline> bookingsAirlines;

	//bi-directional many-to-one association to Flight
    @ManyToOne
	@JoinColumn(name="FLIGHTID")
	private Flight flight;

	//bi-directional many-to-one association to RoutesAirline
    @ManyToOne
	@JoinColumn(name="ROUTEID")
	private RoutesAirline routesAirline;

    public FlightRoute() {
    }

	public long getFlightrouteid() {
		return this.flightrouteid;
	}

	public void setFlightrouteid(long flightrouteid) {
		this.flightrouteid = flightrouteid;
	}

	public String getArrivaltime() {
		return this.arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public Double getCostperticket() {
		return this.costperticket;
	}

	public void setCostperticket(Double costperticket) {
		this.costperticket = costperticket;
	}

	public String getDeparturetime() {
		return this.departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public Set<BookingsAirline> getBookingsAirlines() {
		return this.bookingsAirlines;
	}

	public void setBookingsAirlines(Set<BookingsAirline> bookingsAirlines) {
		this.bookingsAirlines = bookingsAirlines;
	}
	
	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public RoutesAirline getRoutesAirline() {
		return this.routesAirline;
	}

	public void setRoutesAirline(RoutesAirline routesAirline) {
		this.routesAirline = routesAirline;
	}
	
}