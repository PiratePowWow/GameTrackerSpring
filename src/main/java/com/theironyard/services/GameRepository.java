package com.theironyard.services;

import com.theironyard.entities.Game;
import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by PiratePowWow on 3/8/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer>{
    List<Game> findByUser(User user);
    List<Game> findByUserAndGenre(String genre, User user);
    List<Game> findByUserAndGenreAndReleaseYear(String genre, int releaseYear, User user);
    List<Game> findByUserAndGenreAndReleaseYearIsGreaterThanEqual(String genre, int minReleaseYear, User user);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%")
    List<Game> findByApproximatePlatform(String platform);
}
