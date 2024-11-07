package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the special_conndition database table.
 * 
 */
@Entity
@Table(name="special_conndition")
@NamedQuery(name="SpecialConndition.findAll", query="SELECT s FROM SpecialConndition s")
public class SpecialConndition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-many association to Pet
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="pet_has_special_conndition"
		, joinColumns={
			@JoinColumn(name="special_conndition_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="pet_id")
			}
		)
	private List<Pet> pets;

	//bi-directional many-to-one association to NoteForCondition
	@JsonIgnore
	@OneToMany(mappedBy="specialConndition")
	private List<NoteForCondition> noteForConditions;

	public SpecialConndition() {
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

	public List<Pet> getPets() {
		return this.pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<NoteForCondition> getNoteForConditions() {
		return this.noteForConditions;
	}

	public void setNoteForConditions(List<NoteForCondition> noteForConditions) {
		this.noteForConditions = noteForConditions;
	}

	public NoteForCondition addNoteForCondition(NoteForCondition noteForCondition) {
		getNoteForConditions().add(noteForCondition);
		noteForCondition.setSpecialConndition(this);

		return noteForCondition;
	}

	public NoteForCondition removeNoteForCondition(NoteForCondition noteForCondition) {
		getNoteForConditions().remove(noteForCondition);
		noteForCondition.setSpecialConndition(null);

		return noteForCondition;
	}

}