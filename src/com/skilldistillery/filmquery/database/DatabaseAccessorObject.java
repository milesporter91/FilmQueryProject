package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	String USERNAME = "student";
	String PASSWORD = "student";
	public static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	@Override
	public Film findFilmById(int filmId) {
		Film foundFilm = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = "SELECT * FROM film f WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			List<Actor> actorList = findActorsByFilmId(filmId);
			List<String> categories = findCategoriesByFilmId(filmId);
			String language = findLanguageByFilmId(filmId);
			// Extract results
			ResultSet filmData = pst.executeQuery();
			if (filmData.next()) {
			foundFilm = new Film(filmData.getInt("id"), filmData.getString("title"), filmData.getString("description"),
					filmData.getInt("release_year"), filmData.getInt("language_id"), filmData.getInt("rental_duration"),
					filmData.getDouble("rental_rate"), filmData.getInt("length"), filmData.getDouble("replacement_cost"),
					filmData.getString("rating"), filmData.getString("special_features"), actorList, language, categories);
			}
			filmData.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundFilm;

	}

	@Override
	public Actor findActorById(int actorId) {
		Actor foundActor = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = "SELECT * FROM actor a WHERE a.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, actorId);
			// Extract results
			ResultSet actorData = pst.executeQuery();
			actorData.next();
			foundActor = new Actor(actorData.getInt("a.id"), actorData.getString("a.first_name"), actorData.getString("a.last_name"));
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundActor;
	}
	@Override
	public List<String> findCategoriesByFilmId(int filmId) {
		List<String> categoriesOfFilm = new LinkedList<>();
		String foundCategory = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT c.name "
					+ "FROM category c JOIN film_category fc ON c.id = fc.category_id "
					+ "JOIN film f ON fc.film_id = f.id WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet categoryData = pst.executeQuery();
			while (categoryData.next()) {
				foundCategory = categoryData.getString("c.name") + " | ";
				categoriesOfFilm.add(foundCategory);
			}
			categoryData.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriesOfFilm;
	}
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsInFilm = new LinkedList<>();
		Actor foundActor = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT a.id, a.first_name, a.last_name "
					+ "FROM actor a JOIN film_actor fa ON a.id = fa.actor_id JOIN film f ON fa.film_id = f.id WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet actorData = pst.executeQuery();
			while (actorData.next()) {
				foundActor = new Actor(actorData.getInt("a.id"), actorData.getString("a.first_name"), actorData.getString("a.last_name"));
				actorsInFilm.add(foundActor);
			}
			actorData.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorsInFilm;
	}
	
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> foundFilms = new LinkedList<>();
		Film foundFilm;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// Prepare statement
			String sql = "SELECT * FROM film f WHERE f.title LIKE ? OR f.description LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet filmData = pst.executeQuery();
			while (filmData.next()) {
				int filmId = filmData.getInt("f.id");
				List<Actor> actorList = findActorsByFilmId(filmId);
				List<String> categories = findCategoriesByFilmId(filmId);
				String language = findLanguageByFilmId(filmId);
			foundFilm = new Film(filmData.getInt("id"), filmData.getString("title"), filmData.getString("description"),
					filmData.getInt("release_year"), filmData.getInt("language_id"), filmData.getInt("rental_duration"),
					filmData.getDouble("rental_rate"), filmData.getInt("length"), filmData.getDouble("replacement_cost"),
					filmData.getString("rating"), filmData.getString("special_features"), actorList, language, categories);
			foundFilms.add(foundFilm);
			}
			filmData.close();
			pst.close();
			conn.close();
			
	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundFilms;

	}
	
	public String findLanguageByFilmId(int filmId) {
		String language = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT language.name FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet languageData = pst.executeQuery();
			while (languageData.next()) {
			language = languageData.getString("name");
			}
			languageData.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return language;
	}
	
}
