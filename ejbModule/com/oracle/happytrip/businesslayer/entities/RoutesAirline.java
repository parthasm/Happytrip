package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the ROUTES_AIRLINE database table.
 * 
 */
@Entity
@Table(name="ROUTES_AIRLINE")
public class RoutesAirline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROUTES_AIRLINE_ROUTEID_GENERATOR", sequenceName="S_ROUTES_AIRLINE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROUTES_AIRLINE_ROUTEID_GENERATOR")
	private long routeid;

	private BigDecimal distanceinkms;

	private String durationinhrs;

	//bi-directional many-to-one association to FlightRoute
	@OneToMany(mappedBy="routesAirline")
	private Set<FlightRoute> flightRoutes;

	//bi-directional many-to-one association to City
    @ManyToOne
	@JoinColumn(name="TOCITYID")
	private City city1;

	//bi-directional many-to-one association to City
    @ManyToOne
	@JoinColumn(name="FROMCITYID")
	private City city2;

    public RoutesAirline() {
    }

	public long getRouteid() {
		return this.routeid;
	}

	public void setRouteid(long routeid) {
		this.routeid = routeid;
	}

	public BigDecimal getDistanceinkms() {
		return this.distanceinkms;
	}

	public void setDistanceinkms(BigDecimal distanceinkms) {
		this.distanceinkms = distanceinkms;
	}

	public String getDurationinhrs() {
		return this.durationinhrs;
	}

	public void setDurationinhrs(String durationinhrs) {
		this.durationinhrs = durationinhrs;
	}

	public Set<FlightRoute> getFlightRoutes() {
		return this.flightRoutes;
	}

	public void setFlightRoutes(Set<FlightRoute> flightRoutes) {
		this.flightRoutes = flightRoutes;
	}
	
	public City getCity1() {
		return this.city1;
	}

	public void setCity1(City city1) {
		this.city1 = city1;
	}
	
	public City getCity2() {
		return this.city2;
	}

	public void setCity2(City city2) {
		this.city2 = city2;
	}
	
}