package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the note_for_condition database table.
 * 
 */
@Entity
@Table(name="note_for_condition")
@NamedQuery(name="NoteForCondition.findAll", query="SELECT n FROM NoteForCondition n")
public class NoteForCondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String note;

	//bi-directional many-to-one association to Pet
	@ManyToOne
	private Pet pet;

	//bi-directional many-to-one association to SpecialConndition
	@ManyToOne
	@JoinColumn(name="special_conndition_id")
	private SpecialConndition specialConndition;

	public NoteForCondition() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public SpecialConndition getSpecialConndition() {
		return this.specialConndition;
	}

	public void setSpecialConndition(SpecialConndition specialConndition) {
		this.specialConndition = specialConndition;
	}

}