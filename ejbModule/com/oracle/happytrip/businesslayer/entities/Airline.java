package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AIRLINES database table.
 * 
 */
@Entity
@Table(name="AIRLINES")
public class Airline implements Serializable {
	private static final long serialVersionUID = 1L;
	private long airlineid;
	private String airlinecode;
	private String airlinename;
	private Set<Flight> flights;

    public Airline() {
    }


	@Id
	@SequenceGenerator(name="AIRLINES_AIRLINEID_GENERATOR", sequenceName="S_AIRLINES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AIRLINES_AIRLINEID_GENERATOR")
	public long getAirlineid() {
		return this.airlineid;
	}

	public void setAirlineid(long airlineid) {
		this.airlineid = airlineid;
	}


	public String getAirlinecode() {
		return this.airlinecode;
	}

	public void setAirlinecode(String airlinecode) {
		this.airlinecode = airlinecode;
	}


	public String getAirlinename() {
		return this.airlinename;
	}

	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}


	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airline")
	public Set<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}
	
}