package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the pet database table.
 * 
 */
@Entity
@NamedQuery(name="Pet.findAll", query="SELECT p FROM Pet p")
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Owner
	@ManyToOne
	private Owner owner;

	//bi-directional many-to-one association to Specy
	@ManyToOne
	@JoinColumn(name="species_id")
	private Specy specy;

	//bi-directional many-to-many association to SpecialConndition
	@JsonIgnore
	@ManyToMany(mappedBy="pets")
	private List<SpecialConndition> specialConnditions;

	//bi-directional many-to-one association to NoteForCondition
	@JsonIgnore
	@OneToMany(mappedBy="pet")
	private List<NoteForCondition> noteForConditions;

	public Pet() {
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

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Specy getSpecy() {
		return this.specy;
	}

	public void setSpecy(Specy specy) {
		this.specy = specy;
	}

	public List<SpecialConndition> getSpecialConnditions() {
		return this.specialConnditions;
	}

	public void setSpecialConnditions(List<SpecialConndition> specialConnditions) {
		this.specialConnditions = specialConnditions;
	}

	public List<NoteForCondition> getNoteForConditions() {
		return this.noteForConditions;
	}

	public void setNoteForConditions(List<NoteForCondition> noteForConditions) {
		this.noteForConditions = noteForConditions;
	}

	public NoteForCondition addNoteForCondition(NoteForCondition noteForCondition) {
		getNoteForConditions().add(noteForCondition);
		noteForCondition.setPet(this);

		return noteForCondition;
	}

	public NoteForCondition removeNoteForCondition(NoteForCondition noteForCondition) {
		getNoteForConditions().remove(noteForCondition);
		noteForCondition.setPet(null);

		return noteForCondition;
	}

}