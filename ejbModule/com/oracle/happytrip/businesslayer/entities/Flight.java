package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the FLIGHTS database table.
 * 
 */
@Entity
@Table(name="FLIGHTS")
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FLIGHTS_FLIGHTID_GENERATOR", sequenceName="S_FLIGHTS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FLIGHTS_FLIGHTID_GENERATOR")
	private long flightid;

	private String flightname;

	private int noofseats;

	//bi-directional many-to-one association to Airline
    @ManyToOne
	@JoinColumn(name="AIRLINEID")
	private Airline airline;

	//bi-directional many-to-one association to FlightRoute
	@OneToMany(mappedBy="flight")
	private Set<FlightRoute> flightRoutes;

    public Flight() {
    }

	public long getFlightid() {
		return this.flightid;
	}

	public void setFlightid(long flightid) {
		this.flightid = flightid;
	}

	public String getFlightname() {
		return this.flightname;
	}

	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}

	public int getNoofseats() {
		return this.noofseats;
	}

	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}

	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public Set<FlightRoute> getFlightRoutes() {
		return this.flightRoutes;
	}

	public void setFlightRoutes(Set<FlightRoute> flightRoutes) {
		this.flightRoutes = flightRoutes;
	}
	
}