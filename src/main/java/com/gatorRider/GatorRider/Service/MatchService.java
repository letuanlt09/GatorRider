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
            {"Ocala", "Orlando", "Port St. Lucie", "West Palm Beach", "Fort Lauderdale", "Miami"},
            {"Ocala", "Tampa", "Cape Coral", "Fort Lauderdale", "Miami"}
    };

    public List<Ride> findMatchRide(RideRequest rideRequest) {
        List<Ride> result = new ArrayList<>();
        List<String> matchLocations = new ArrayList<>();
        matchLocations = rideRequest.getIsOutBound() ? getOutBoundList(rideRequest) : getInBoundList(rideRequest);

        result = rideRepository.findByLocationInAndDateTimeBetweenAndIsOutBound(matchLocations,
                rideRequest.getTimeFrom(),
                rideRequest.getTimeTo(),
                rideRequest.getIsOutBound());

        return result;
    }

    List<String> getOutBoundList(RideRequest rideRequest) {
        List<String> matchLocations = new ArrayList<>();
        matchLocations.add(rideRequest.getLocation());

        for (String[] route : routes) {
            boolean allow = false;
            for (String location : route) {
                if (location.equals(rideRequest.getLocation())) {
                    allow = true;
                }
                else if (allow) {
                    matchLocations.add(location);
                }
            }
        }
        return matchLocations;
    }

    List<String> getInBoundList(RideRequest rideRequest) {
        List<String> matchLocations = new ArrayList<>();
        matchLocations.add(rideRequest.getLocation());

        for (String[] route : routes) {
            boolean allow = false;
            for (int i = route.length - 1; i > -1; i--) {
                if (route[i].equals(rideRequest.getLocation())) {
                    allow = true;
                }
                else if (allow) {
                    matchLocations.add(route[i]);
                }
            }
        }
        return matchLocations;
    }

}