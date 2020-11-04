package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService implements org.hibernate.service.Service {

    @Autowired
    private RideRepository rideRepository;

    String[][] routes = {
            {"Talahassee"},
            {"Jacksonville"},
            {"Orlando", "Port St. Lucie", "Miami"},
            {"Tampa", "Cape Coral", "Miami"}
    };

    public List<Ride> findMatchRide(RideRequest rideRequest) {
        List<Ride> result = new ArrayList<>();
        List<String> matchDestinations = new ArrayList<>();
        matchDestinations.add(rideRequest.getDestination());
        for (String[] route : routes) {
            boolean allow = false;
            for (String destination : route) {
                if (destination.equals(rideRequest.getDestination())) {
                    allow = true;
                }
                else if (allow) {
                    matchDestinations.add(destination);
                }
            }
        }

        Calendar startDay = GregorianCalendar.getInstance();
        Calendar endDay = GregorianCalendar.getInstance();

        startDay.setTime(rideRequest.getDate());
        endDay.setTime(rideRequest.getDate());
        startDay.add(GregorianCalendar.DAY_OF_WEEK, -3 );
        endDay.add(GregorianCalendar.DAY_OF_WEEK, 3);

        result = rideRepository.findByDestinationInAndDateBetween(matchDestinations,
                startDay.getTime(),
                endDay.getTime());

        result.sort(Comparator.comparingInt(a -> Math.abs(a.getDate().compareTo(rideRequest.getDate()))));

        return result;
//
//        return result.stream().filter(ride -> {
//            return (ride.getDate().after(startDay.getTime()) && ride.getDate().before(endDay.getTime()));
//        }).collect(Collectors.toList());
    }

}