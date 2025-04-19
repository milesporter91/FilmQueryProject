package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	// FIELDS
	private Integer id;
	private String title;
	private String description;
	private int releaseYear;
	private Integer languageID;
	private Integer rentalDuration;
	private Double rentalRate;
	private int length;
	private Double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actorList;
	private String language;
	private List<String> categories;

	// CONSTRUCTORS
	public Film(Integer id, String title, String description, int releaseYear, Integer languageID,
			Integer rentalDuration, Double rentalRate, int length, Double replacementCost, String rating,
			String specialFeatures, List<Actor> actorList, String language, List<String> categories) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageID = languageID;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.actorList = actorList;
		this.language = language;
		this.categories = categories;
	}

	public Film() {
		super();
	}

	// GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageID() {
		return languageID;
	}

	public void setLanguageID(Integer languageID) {
		this.languageID = languageID;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	// toString OVERRIDE
	@Override
	public String toString() {
		return "Film ID: " + id + " | Title: " + title + " | Description: " + description + " | Release Year: " + releaseYear
				+ " | Language ID: " + languageID + " | Rental Duration: " + rentalDuration + " | Rental Rate: " + rentalRate
				+ " | Length: " + length + " | Replacement Cost: " + replacementCost + " Rating: " + rating
				+ " | Special Features: " + specialFeatures + " | Actors: " + actorList;
	}

}


