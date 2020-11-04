package com.gatorRider.GatorRider.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@ToString
public class Ride {

    @Id
    @Getter
    @Setter
    private String id;

    @NonNull
    @Getter
    @Setter
    private Timestamp dateTime;

    @NonNull
    @Getter
    @Setter
    private String destination;

    @NonNull
    @Getter
    @Setter
    private Boolean isOutBound;

    @NonNull
    @Getter
    @Setter
    private int numSeatAvailable;

    @NonNull
    @Getter
    @Setter
    private int modelYear;

    @NonNull
    @Getter
    @Setter
    private String modelName;

    @NonNull
    @Getter
    @Setter
    private String rideIntro;

    @NonNull
    @ManyToOne
    @Getter
    @Setter
    private Driver driver;


}
