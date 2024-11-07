package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the permit_type database table.
 * 
 */
@Entity
@Table(name="permit_type")
@NamedQuery(name="PermitType.findAll", query="SELECT p FROM PermitType p")
public class PermitType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Permit
	@JsonIgnore
	@OneToMany(mappedBy="permitType")
	private List<Permit> permits;

	//bi-directional many-to-one association to Specy
	@ManyToOne
	private Specy species;

	public PermitType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<Permit> getPermits() {
		return this.permits;
	}

	public void setPermits(List<Permit> permits) {
		this.permits = permits;
	}

	public Permit addPermit(Permit permit) {
		getPermits().add(permit);
		permit.setPermitType(this);

		return permit;
	}

	public Permit removePermit(Permit permit) {
		getPermits().remove(permit);
		permit.setPermitType(null);

		return permit;
	}

	public Specy getSpecies() {
		return this.species;
	}

	public void setSpecies(Specy species) {
		this.species = species;
	}

}