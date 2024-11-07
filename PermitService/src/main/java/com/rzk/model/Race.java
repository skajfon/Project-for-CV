package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the race database table.
 * 
 */
@Entity
@NamedQuery(name="Race.findAll", query="SELECT r FROM Race r")
public class Race implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min=3)
	private String name;

	//bi-directional many-to-one association to Specy
	@JsonIgnore
	@OneToMany(mappedBy="race")
	private List<Specy> species;

	public Race() {
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

	public List<Specy> getSpecies() {
		return this.species;
	}

	public void setSpecies(List<Specy> species) {
		this.species = species;
	}

	public Specy addSpecy(Specy specy) {
		getSpecies().add(specy);
		specy.setRace(this);

		return specy;
	}

	public Specy removeSpecy(Specy specy) {
		getSpecies().remove(specy);
		specy.setRace(null);

		return specy;
	}

}