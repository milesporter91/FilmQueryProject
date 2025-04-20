package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }
  
  @Test
  void test_findFilmById_returns_film_with_id() {
    Film f = db.findFilmById(1);
    assertNotNull(f);
    assertEquals("ACADEMY DINOSAUR", f.getTitle());
  }

  @Test
  void test_findFilmById_with_invalid_id_returns_null() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }
  
  @Test
  void test_findActorById() {
	  Actor a = db.findActorById(1);
	  assertNotNull(a);
	  assertEquals("Penelope", a.getFirstName());
  }
  
  @Test
  void test_findActorsByFilmId() {
	  List<Actor> actorsList = db.findActorsByFilmId(2);
	  assertNotNull(actorsList);
	  assertEquals("Bob", actorsList.get(0).getFirstName());
	  assertEquals("Minnie", actorsList.get(1).getFirstName());
  }
  
  @Test
  void test_findFilmByKeword() {
	  List<Film> foundFilms = db.findFilmByKeyword("uncut");
	  assertNotNull(foundFilms);
	  assertEquals( 3, foundFilms.size() );
	  assertEquals(921, foundFilms.get(2).getId());
  }
  
  @Test
  void test_findLanguageByFilmId() {
	  String language = db.findLanguageByFilmId(16);
	  assertNotNull(language);
	  assertEquals("English", language);
  }
  
  @Test
  void test_findCategoriesByFilmId() {
	  List<String> categories = db.findCategoriesByFilmId(16);
	  assertNotNull(categories);
	  assertEquals("Foreign", categories.get(0));
  }
  
  @Test
  void test_findRentalCopiesByFilmId() {
	  List<String> rentalCopies = db.findRentalCopiesByFilmId(17);
	  assertNotNull(rentalCopies);
	  assertEquals(17, rentalCopies.size());
	  assertEquals("4847 Used", rentalCopies.get(12));
  }
  
  
  
  
}
