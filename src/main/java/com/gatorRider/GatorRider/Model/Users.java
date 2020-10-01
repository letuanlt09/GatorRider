package com.gatorRider.GatorRider.Model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

    public Long getId() {
        return id;
    }

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

    @NonNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(@NonNull String fullName) {
        this.fullName = fullName;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NonNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
