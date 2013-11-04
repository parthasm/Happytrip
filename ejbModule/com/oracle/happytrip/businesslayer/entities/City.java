package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(name="CITIES")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CITIES_CITYID_GENERATOR", sequenceName="S_CITIES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITIES_CITYID_GENERATOR")
	private long cityid;

	private String cityname;

	//bi-directional many-to-one association to RoutesAirline
	@OneToMany(mappedBy="city1")
	private Set<RoutesAirline> routesAirlines1;

	//bi-directional many-to-one association to RoutesAirline
	@OneToMany(mappedBy="city2")
	private Set<RoutesAirline> routesAirlines2;

    public City() {
    }

	public long getCityid() {
		return this.cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Set<RoutesAirline> getRoutesAirlines1() {
		return this.routesAirlines1;
	}

	public void setRoutesAirlines1(Set<RoutesAirline> routesAirlines1) {
		this.routesAirlines1 = routesAirlines1;
	}
	
	public Set<RoutesAirline> getRoutesAirlines2() {
		return this.routesAirlines2;
	}

	public void setRoutesAirlines2(Set<RoutesAirline> routesAirlines2) {
		this.routesAirlines2 = routesAirlines2;
	}
	
}