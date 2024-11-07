package com.rzk.model;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the owner database table.
 * 
 */
@Entity
@NamedQuery(name="Owner.findAll", query="SELECT o FROM Owner o")
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@NotNull
	@Size(min=3)
	@Column(name="last_name")
	private String lastName;
	
	
	@NotNull
	@Size(min=3)
	private String name;

	//bi-directional many-to-one association to Permit
	@JsonIgnore
	@OneToMany(mappedBy="owner")
	private List<Permit> permits;

	public Owner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permit> getPermits() {
		return this.permits;
	}

	public void setPermits(List<Permit> permits) {
		this.permits = permits;
	}

	public Permit addPermit(Permit permit) {
		getPermits().add(permit);
		permit.setOwner(this);

		return permit;
	}

	public Permit removePermit(Permit permit) {
		getPermits().remove(permit);
		permit.setOwner(null);

		return permit;
	}

}