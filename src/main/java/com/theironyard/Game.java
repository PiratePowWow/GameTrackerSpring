package com.theironyard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by PiratePowWow on 3/8/16.
 */
@Entity
public class Game {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String platform;
    @NotNull
    String genre;
    int releaseYear;

    public Game(){

    }

    public Game(int id, String name, String platform, String genre, int releaseYear) {
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public Game(String name, String platform, String genre, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
