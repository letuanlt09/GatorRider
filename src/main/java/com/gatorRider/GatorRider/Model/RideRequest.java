package com.gatorRider.GatorRider.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class RideRequest {
    private Date date;
    private Time time;
    private Timestamp dateTime;
    private Timestamp timeFrom;
    private Timestamp timeTo;
    private String destination;
    private Boolean isOutBound;
    private String modelName;
    private int modelYear;
    private int numSeatAvailable;
    private String rideIntro;
    private String driverId;
    private String rideId;
    public void validation () throws Exception{
        if(numSeatAvailable>9){
            throw new Exception("Number of Seat must less or equal than 9");
        }
    }
}
