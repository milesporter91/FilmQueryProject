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
			// Extract results
			ResultSet filmData = pst.executeQuery();
			if (filmData.next()) {
			foundFilm = new Film(filmData.getInt("id"), filmData.getString("title"), filmData.getString("description"),
					filmData.getInt("release_year"), filmData.getInt("language_id"), filmData.getInt("rental_duration"),
					filmData.getDouble("rental_rate"), filmData.getInt("length"), filmData.getDouble("replacement_cost"),
					filmData.getString("rating"), filmData.getString("special_features"), actorList);
			}
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
			return foundActor;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundActor;
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
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				foundActor = new Actor(rs.getInt("a.id"), rs.getString("a.first_name"), rs.getString("a.last_name"));
				actorsInFilm.add(foundActor);
			}
			return actorsInFilm;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorsInFilm;
	}
}
