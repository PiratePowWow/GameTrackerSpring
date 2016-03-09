package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by PiratePowWow on 3/8/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer>{
    List<Game> findByGenre(String genre);
    List<Game> findByGenreAndReleaseYear(String genre, int releaseYear);
    List<Game> findByGenreAndReleaseYearIsGreaterThanEqual(String genre, int minReleaseYear);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%")
    List<Game> findByApproximatePlatform(String platform);
}
