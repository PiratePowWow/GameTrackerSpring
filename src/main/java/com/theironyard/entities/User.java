package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by PiratePowWow on 3/9/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @NotNull
    @Column(unique = true)
    String name;

    @NotNull
    String passwordHash;


    public User(){

    }

    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
