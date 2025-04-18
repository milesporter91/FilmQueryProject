package com.skilldistillery.filmquery.entities;

public class Actor {
	
	// FIELDS
	private Integer id;
	private String firstName;
	private String lastName;
	
	// Constructors
	
	public Actor(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor() {
		super();
	}

	// GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// toString OVERRIDE
	@Override
	public String toString() {
		return "Actor Name: " + firstName + " " + lastName;
	}
	
	
}
