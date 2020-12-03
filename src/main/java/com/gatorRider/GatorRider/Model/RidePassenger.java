package com.gatorRider.GatorRider.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Entity
@Table(name = "ride_passenger2")
public class RidePassenger {
    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String rideId;
    @Setter
    @Getter
    private String passengerId;
}
