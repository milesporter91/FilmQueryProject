package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	boolean keepGoing = true;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		startUserInterface();

		input.close();
	}

	private void startUserInterface() {
		while (keepGoing) {
			printMenu();
			runUserChoice();

		}
	}

	public int getFilmId() {
		try {
			System.out.println("Please enter a film ID number: ");
			int filmId = input.nextInt();
			input.nextLine();
			return filmId;
		} catch (Exception e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("That is not a valid number.");
			System.out.println("-------------------------------------------------------------------------");
			input.nextLine();
			getFilmId();
		}
		return 0;
	}

	public String getSearchQuery() {
		System.out.println("Please enter a keyword to search for a film: ");
		String filmSearchQuery = input.nextLine();
		return filmSearchQuery;
	}

	public void runUserChoice() {
		String menuChoice = input.nextLine();
		switch (menuChoice) {
		case "1": {
			int filmId = getFilmId();
			Film foundFilm = db.findFilmById(filmId);
			printFilm(foundFilm);
			getFilmDetailsOrMainMenu(foundFilm);
			break;
		}
		case "2": {
			String filmSearchQuery = getSearchQuery();
			List<Film> foundFilmsKeyword = db.findFilmByKeyword(filmSearchQuery);
			printFilms(foundFilmsKeyword);
			getFilmDetailsOrMainMenu(foundFilmsKeyword);
			break;
		}
		case "3": {
			System.out.println("Goodbye!");
			System.exit(0);
		}
		}
	}

	public void printFilm(Film foundFilm) {
		if (foundFilm == null) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("We don't have a film with that ID");
			System.out.println("-------------------------------------------------------------------------");
			startUserInterface();
		} else {
			String actorList = printActorList(foundFilm);
			String categoryList = printCategoryList(foundFilm);
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Language: " + foundFilm.getLanguage() + "\nTitle: " + foundFilm.getTitle()
					+ "\nYear Released: " + foundFilm.getReleaseYear() + "\nRated: " + foundFilm.getRating()
					+ "\nDescription: " + foundFilm.getDescription() + "\nActors: |" + actorList + "\nCategories: |"
					+ categoryList);
			System.out.println("-------------------------------------------------------------------------");
		}
	}

	public void printFilms(List<Film> foundFilmsKeyword) {
		if (foundFilmsKeyword.isEmpty()) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("We don't have any films containing that keyword");
			System.out.println("-------------------------------------------------------------------------");
			startUserInterface();
		} else {
			for (int i = 0; i < foundFilmsKeyword.size(); i++) {
				printFilm(foundFilmsKeyword.get(i));
			}
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Search results: " + foundFilmsKeyword.size());
			System.out.println("-------------------------------------------------------------------------");

		}

	}

	public void getFilmDetailsOrMainMenu(List<Film> foundFilmsKeyword) {
		try {
			System.out.println();
			System.out.println("Would you like to view a film's details, or return to the main menu?");
			System.out.println("1. Details		2. Main Menu");
			int userChoice = input.nextInt();
			input.nextLine();
			if (userChoice == 1) {
				for (int i = 0; i < foundFilmsKeyword.size(); i++) {
					System.out.println(i + 1 + ": " + foundFilmsKeyword.get(i).getTitle());
				}
				System.out.println("Please choose a film to see details (Enter the number assigned): ");
				userChoice = input.nextInt();
				input.nextLine();
				Film foundFilm = foundFilmsKeyword.get(userChoice - 1);
				printFilmDetails(foundFilm);
			}
			else if (userChoice == 2) {
				startUserInterface();
			}
			else {
				System.out.println("Please choose a valid option..");
				getFilmDetailsOrMainMenu(foundFilmsKeyword);
			}
		} catch (Exception e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("That is not a valid number.");
			System.out.println("-------------------------------------------------------------------------");
			input.nextLine();
			getFilmDetailsOrMainMenu(foundFilmsKeyword);
		}
	}

	public void getFilmDetailsOrMainMenu(Film foundFilm) {
		try {
			System.out.println();
			System.out.println("Would you like to view a film's details, or return to the main menu?");
			System.out.println("1. Details		2. Main Menu");
			int userChoice = input.nextInt();
			input.nextLine();
			if (userChoice == 1) {
				printFilmDetails(foundFilm);
			}
		} catch (Exception e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("That is not a valid number.");
			System.out.println("-------------------------------------------------------------------------");
			input.nextLine();
			getFilmDetailsOrMainMenu(foundFilm);
		}
	}

	public String printActorList(Film foundFilm) {
		String actorList = "";
		for (int j = 0; j < foundFilm.getActorList().size(); j++) {
			actorList += " " + foundFilm.getActorList().get(j).getFirstName() + " "
					+ foundFilm.getActorList().get(j).getLastName() + " |";
		}
		return actorList;
	}

	public String printRentalCopiesList(Film foundFilm) {
		String rentalCopies = "";
		int copyNumber = 1;
		for (int j = 0; j < foundFilm.getActorList().size(); j++) {
			rentalCopies += "Copy #" + copyNumber + ": " + foundFilm.getRentalCopiesList().get(j) + " | ";
			copyNumber++;
		}
		return rentalCopies;
	}

	public String printCategoryList(Film foundFilm) {
		String categoryList = "";
		for (int k = 0; k < foundFilm.getCategories().size(); k++) {
			categoryList += " " + foundFilm.getCategories().get(k) + " |";
		}
		return categoryList;
	}

	public void printFilmDetails(Film foundFilm) {

		System.out.println("Film ID: " + foundFilm.getId());
		System.out.println("Title: " + foundFilm.getTitle());
		System.out.println("Rating: " + foundFilm.getRating());
		System.out.println("Film Categories: " + printCategoryList(foundFilm));
		System.out.println("Description: " + foundFilm.getDescription());
		System.out.println("Release Year: " + foundFilm.getReleaseYear());
		System.out.println("Language ID: " + foundFilm.getLanguageID());
		System.out.println("Language: " + foundFilm.getLanguage());
		System.out.println("Length: " + foundFilm.getLength() + " minutes");
		System.out.println("Actor List: " + printActorList(foundFilm));
		System.out.println("Special Features: " + foundFilm.getSpecialFeatures());
		System.out.println("Rental Duration: " + foundFilm.getRentalDuration());
		System.out.println("Rental Rate: " + foundFilm.getRentalRate());
		System.out.println("Replacement Cost: " + foundFilm.getReplacementCost());
		System.out.println("Rental Copy List: " + printRentalCopiesList(foundFilm));
		System.out.println("-------------------------------------------------------------------------");
	}

	public void printMenu() {
		System.out.println("Please select an option by entering the number assigned");
		System.out.println("1: Look up film by ID number");
		System.out.println("2: Look up film by keyword");
		System.out.println("3: Exit");
	}

}
