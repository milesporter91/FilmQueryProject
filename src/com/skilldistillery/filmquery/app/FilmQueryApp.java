package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	boolean keepGoing = true;

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		while (keepGoing) {
			printMenu();
			String menuChoice = input.nextLine();
			runUserChoice(input, menuChoice);
			
			
		}
	}

	public void runUserChoice(Scanner input, String menuChoice) {
		switch (menuChoice) {
		case "1": {
			System.out.println("Please enter a film ID number: ");
			int filmId = input.nextInt();
			input.nextLine();
			Film foundFilm = db.findFilmById(filmId);
			printFilm(foundFilm);
			break;
		}
		case "2": {
			System.out.println("Please enter a keyword to search for a film: ");
			String filmSearchQuery = input.nextLine();
			List<Film> foundFilmsKeyword = db.findFilmByKeyword(filmSearchQuery);
			printFilms(input, foundFilmsKeyword);
			break;
		}
		case "3": {
			System.out.println("Goodbye!");
			keepGoing = false;
			break;
		}
	}
	}
	
	public void printFilm(Film foundFilm) {
		if (foundFilm == null) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("We don't have a film with that ID");
			System.out.println("-------------------------------------------------------------------------");
		} else {
			String actorList = "";
			for (int j = 0; j < foundFilm.getActorList().size(); j++) {
				actorList += " " + foundFilm.getActorList().get(j).getFirstName() + " "
						+ foundFilm.getActorList().get(j).getLastName() + " |";
			}
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Language: " + foundFilm.getLanguage() + "\nTitle: " + foundFilm.getTitle()
					+ "\nYear Released: " + foundFilm.getReleaseYear() + "\nRated: " + foundFilm.getRating()
					+ "\nDescription: " + foundFilm.getDescription() + "\nActors: |" + actorList);
			System.out.println("-------------------------------------------------------------------------");
		}
	}
	
	public void printFilms(Scanner input, List<Film> foundFilmsKeyword) {
		if (foundFilmsKeyword.isEmpty()) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("We don't have any films containing that keyword");
			System.out.println("-------------------------------------------------------------------------");
		} else {
			for (int i = 0; i < foundFilmsKeyword.size(); i++) {
				printFilm(foundFilmsKeyword.get(i));
			}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Search results: " + foundFilmsKeyword.size());
		System.out.println("-------------------------------------------------------------------------");
		
		System.out.println();
		System.out.println("Would you like to view a film's details, or return to the main menu?");
		System.out.println("1. Details		2. Main Menu");
		int userChoice = input.nextInt();
		input.nextLine();
		if (userChoice == 1) {
			for (int i = 0; i < foundFilmsKeyword.size(); i++) {
				System.out.println(i+1 + ": " + foundFilmsKeyword.get(i).getTitle());
			}
			System.out.println("Please choose a film to see details (Enter the number assigned): ");
			userChoice = input.nextInt();
			input.nextLine();
			Film foundFilm = foundFilmsKeyword.get(userChoice - 1 );
			printFilmDetails(foundFilm);
		}
		}
		
	}
	
	public void printFilmDetails(Film foundFilm) {
		System.out.println("Film ID: " + foundFilm.getId());
		System.out.println("Title: " + foundFilm.getTitle());
		System.out.println("Rating: " + foundFilm.getRating());
		System.out.println("Description: " + foundFilm.getDescription());
		System.out.println("Release Year: " + foundFilm.getReleaseYear());
		System.out.println("Language ID: " + foundFilm.getLanguageID());
		System.out.println("Language: " + foundFilm.getLanguage());
		System.out.println("Length: " + foundFilm.getLength());
		System.out.println("Actor List: " + foundFilm.getActorList());
		System.out.println("Special Features: " + foundFilm.getSpecialFeatures());
		System.out.println("Rental Duration: " + foundFilm.getRentalDuration());
		System.out.println("Rental Rate: " + foundFilm.getRentalRate());
		System.out.println("Replacement Cost: " + foundFilm.getReplacementCost());
		System.out.println("-------------------------------------------------------------------------");
	}
	public void printMenu() {
		System.out.println("Please select an option by entering the number assigned");
		System.out.println("1: Look up film by ID number");
		System.out.println("2: Look up film by keyword");
		System.out.println("3: Exit");
	}
	
	
}
