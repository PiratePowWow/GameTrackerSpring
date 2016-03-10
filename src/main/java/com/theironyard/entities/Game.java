package com.theironyard.entities;

import javax.persistence.*;
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

    @ManyToOne
    User user;

    public Game(){

    }

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.user = user;
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
