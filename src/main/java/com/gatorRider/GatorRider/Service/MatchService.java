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
        List<String> matchDestinations = new ArrayList<>();
        matchDestinations = rideRequest.getIsOutBound() ? getOutBoundList(rideRequest) : getInBoundList(rideRequest);

        result = rideRepository.findByDestinationInAndDateTimeBetweenAndIsOutBound(matchDestinations,
                rideRequest.getTimeFrom(),
                rideRequest.getTimeTo(),
                rideRequest.getIsOutBound());

        return result;
    }

    List<String> getOutBoundList(RideRequest rideRequest) {
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
        return matchDestinations;
    }

    List<String> getInBoundList(RideRequest rideRequest) {
        List<String> matchDestinations = new ArrayList<>();
        matchDestinations.add(rideRequest.getDestination());

        for (String[] route : routes) {
            boolean allow = false;
            for (int i = route.length - 1; i > -1; i--) {
                if (route[i].equals(rideRequest.getDestination())) {
                    allow = true;
                }
                else if (allow) {
                    matchDestinations.add(route[i]);
                }
            }
        }
        return matchDestinations;
    }

}