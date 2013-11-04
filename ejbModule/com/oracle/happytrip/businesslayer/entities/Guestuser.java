//package com.oracle.happytrip.businesslayer.entities;
//
//
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//
///**
// * The persistent class for the GUESTUSERS database table.
// * 
// */
////@Entity
//@Table(name="GUESTUSERS")
//public class Guestuser extends Genericuser implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	
//
//	//bi-directional one-to-one association to Genericuser
//	@OneToOne
//	@JoinColumn(name="USERID")
//	private Genericuser genericuser;
//
//    public Guestuser() {
//    }
//
//	public void setGenericuser(Genericuser genericuser) {
//		this.genericuser = genericuser;
//	}
//
//	public Genericuser getGenericuser() {
//		return genericuser;
//	}
//
//	
//
//	
//}