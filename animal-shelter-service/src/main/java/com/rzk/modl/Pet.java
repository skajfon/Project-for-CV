package com.rzk.modl;



public class Pet {
	private int id;
	private String name;
	private Owner owner;
	private Specy specy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Specy getSpecy() {
		return specy;
	}
	public void setSpecy(Specy specy) {
		this.specy = specy;
	}
	
}
