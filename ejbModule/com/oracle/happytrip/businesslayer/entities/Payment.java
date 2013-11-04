package com.oracle.happytrip.businesslayer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the PAYMENTS database table.
 * 
 */
@Entity
@Table(name="PAYMENTS")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="PAYMENTS_PAYMENTID_GENERATOR", sequenceName="S_PAYMENTS")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENTS_PAYMENTID_GENERATOR")
	private long paymentid;

	private double paidamount;

    @Temporal( TemporalType.DATE)
	private Date paymentdate;

	private String paymentreferenceno;

	//bi-directional many-to-one association to BookingsAirline
	@OneToMany(mappedBy="payment")
	private Set<BookingsAirline> bookingsAirlines;

    public Payment() {
    }

	public long getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(long paymentid) {
		this.paymentid = paymentid;
	}

	public double getPaidamount() {
		return this.paidamount;
	}

	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}

	public Date getPaymentdate() {
		return this.paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getPaymentreferenceno() {
		return this.paymentreferenceno;
	}

	public void setPaymentreferenceno(String paymentreferenceno) {
		this.paymentreferenceno = paymentreferenceno;
	}

	public Set<BookingsAirline> getBookingsAirlines() {
		return this.bookingsAirlines;
	}

	public void setBookingsAirlines(Set<BookingsAirline> bookingsAirlines) {
		this.bookingsAirlines = bookingsAirlines;
	}
	
}