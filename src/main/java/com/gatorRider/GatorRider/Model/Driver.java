package com.gatorRider.GatorRider.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Driver {
    @Id
    @Getter
    @Setter
    private String id;

    @NonNull
    @Getter
    private String fullName;

    @NonNull
    @Getter
    private String phone;

    @NonNull
    @Getter
    private String email;

    @NonNull
    @Getter
    @Setter
    private String passwordHash;

    @NonNull
    @Getter
    private char gender;

    public Driver(String fullName, String phone, String email, String passwordHash, char gender) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
    }

    public Driver() {

    }
}