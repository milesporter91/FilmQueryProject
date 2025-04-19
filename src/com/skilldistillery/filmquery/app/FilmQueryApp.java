package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	@SuppressWarnings("unused")
	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		Actor actor = db.findActorById(71);
		System.out.println("--------------------------------------");
		System.out.println(actor);
		List<Actor> listOfActors = db.findActorsByFilmId(5);
		System.out.println("--------------------------------------");
		System.out.println(listOfActors);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean keepGoing = true;
		Film foundFilm;
		while (keepGoing) {
			System.out.println("Please select an option by entering the number assigned");
			System.out.println("1: Look up film by ID number");
			System.out.println("2: Look up film by keyword");
			System.out.println("3: Exit");
			String menuChoice = input.nextLine();
			switch (menuChoice) {
			case "1": {
				System.out.println("Please enter a film ID number: ");
				int filmId = input.nextInt();
				input.nextLine();
				foundFilm = db.findFilmById(filmId);
				if (foundFilm == null) {
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("We don't have a film with that ID");
					System.out.println("-------------------------------------------------------------------------");
				} else {
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("Language: " + foundFilm.getLanguage() + "\nTitle: " + foundFilm.getTitle() + "\nYear Released: "
							+ foundFilm.getReleaseYear() + "\nRated: " + foundFilm.getRating() + "\nDescription: "
							+ foundFilm.getDescription());
					System.out.println("-------------------------------------------------------------------------");
				}
				break;
			}
			case "2": {
				System.out.println("Please enter a keyword to search for a film: ");
				String filmSearchQuery = input.nextLine();
				List<Film> foundFilmsKeyword = db.findFilmByKeyword(filmSearchQuery);
				if (foundFilmsKeyword == null) {
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("We don't have a film containing that keyword");
					System.out.println("-------------------------------------------------------------------------");
				} else {
					for (int i = 0; i < foundFilmsKeyword.size(); i++) {
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Language: " + foundFilmsKeyword.get(i).getLanguage() + "\nTitle: " + foundFilmsKeyword.get(i).getTitle() + "\nYear Released: "
								+ foundFilmsKeyword.get(i).getReleaseYear() + "\nRated: " + foundFilmsKeyword.get(i).getRating()
								+ "\nDescription: " + foundFilmsKeyword.get(i).getDescription());
						System.out.println("-------------------------------------------------------------------------");
					}
				}
				break;
			}
			case "3": {
				System.out.println("Goodbye!");
				keepGoing = false;
				break;
			}
			}
		}
	}

}
