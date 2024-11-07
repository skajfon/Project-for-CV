package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the permit database table.
 * 
 */
@Entity
@NamedQuery(name="Permit.findAll", query="SELECT p FROM Permit p")
public class Permit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date issued;


	//bi-directional many-to-one association to Owner
	@ManyToOne
	private Owner owner;

	//bi-directional many-to-one association to PermitType
	@ManyToOne
	@JoinColumn(name="permit_type_id")
	private PermitType permitType;

	public Permit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getIssued() {
		return this.issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}


	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public PermitType getPermitType() {
		return this.permitType;
	}

	public void setPermitType(PermitType permitType) {
		this.permitType = permitType;
	}

}