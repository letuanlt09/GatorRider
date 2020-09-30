package com.gatorRider.GatorRider.Model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
public class Ride {

    @Id
    @GeneratedValue
//    @Column(name= "id")
    private Long id;
    @NonNull
    private Date date;
    @NonNull
    private Time time;
    @NonNull
    private String destination;
    @NonNull
    private int numSeatAvailable;
    @NonNull
    private int modelYear;
    @NonNull
    private String modelName;
    @NonNull
    private String riderIntro;
    @NonNull
    @ManyToOne
    private Users driver;

}
