package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the species database table.
 * 
 */
@Entity
@Table(name="species")
@NamedQuery(name="Specy.findAll", query="SELECT s FROM Specy s")
public class Specy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=3)
	private String name;

	@Column(name="`need-permit`")
	private boolean need_permit;

	//bi-directional many-to-one association to PermitType
	@JsonIgnore
	@OneToMany(mappedBy="species")
	private List<PermitType> permitTypes;

	//bi-directional many-to-one association to Race
	@ManyToOne
	private Race race;

	public Specy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getNeed_permit() {
		return this.need_permit;
	}

	public void setNeed_permit(boolean need_permit) {
		this.need_permit = need_permit;
	}

	public List<PermitType> getPermitTypes() {
		return this.permitTypes;
	}

	public void setPermitTypes(List<PermitType> permitTypes) {
		this.permitTypes = permitTypes;
	}

	public PermitType addPermitType(PermitType permitType) {
		getPermitTypes().add(permitType);
		permitType.setSpecies(this);

		return permitType;
	}

	public PermitType removePermitType(PermitType permitType) {
		getPermitTypes().remove(permitType);
		permitType.setSpecies(null);

		return permitType;
	}

	public Race getRace() {
		return this.race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

}