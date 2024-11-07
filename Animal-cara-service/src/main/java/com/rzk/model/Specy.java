package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
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

	private String name;

	@Column(name="`need-permit`")
	private byte need_permit;

	@Column(name="race_id")
	private int raceId;

	//bi-directional many-to-one association to Pet
	@JsonIgnore
	@OneToMany(mappedBy="specy")
	private List<Pet> pets;

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

	public byte getNeed_permit() {
		return this.need_permit;
	}

	public void setNeed_permit(byte need_permit) {
		this.need_permit = need_permit;
	}

	public int getRaceId() {
		return this.raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public List<Pet> getPets() {
		return this.pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Pet addPet(Pet pet) {
		getPets().add(pet);
		pet.setSpecy(this);

		return pet;
	}

	public Pet removePet(Pet pet) {
		getPets().remove(pet);
		pet.setSpecy(null);

		return pet;
	}

}