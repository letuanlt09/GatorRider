package com.gatorRider.GatorRider.Model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String fullName;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String passwordHash;
    @NonNull
    private char gender;

    public Users(String fullName, String phone, String email, String passwordHash, char gender) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
    }

    public Users() {

    }
}
