package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService implements org.hibernate.service.Service {

    @Autowired
    private RideRepository rideRepository;

    private String[][] routes = {
            {"Tallahassee", "Pensacola"},
            {"Jacksonville"},
            {"Ocala", "Orlando", "Port St. Lucie", "West Palm Beach", "Fort Lauderdale", "Miami"},
            {"Ocala", "Tampa", "Cape Coral", "Naples"}
    };

    private String[] locations = {"Miami",
            "Tampa",
            "Tallahassee",
            "Pensacola",
            "Jacksonville",
            "Ocala",
            "Orlando",
            "Port St. Lucie",
            "West Palm Beach",
            "Fort Lauderdale",
            "Cape Coral",
            "Naples"
    };

    public List<String> getLocations() {
        return Arrays.stream(locations).sorted().collect(Collectors.toList());
    }
    public List<Ride> findMatchRide(RideRequest rideRequest) {
        List<Ride> result = new ArrayList<>();
        List<String> matchLocations = new ArrayList<>();
        matchLocations = rideRequest.getIsOutBound() ? getOutBoundList(rideRequest) : getInBoundList(rideRequest);
        for(String i: matchLocations){
            System.out.println(i);
        }

        result = rideRepository.findByLocationInAndDateTimeBetweenAndIsOutBoundOrderByDateTimeAsc(matchLocations,
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

        for (String[] route : routes) {
            boolean allow = false;
            for (int i = route.length - 1; i > -1; i--) {
                if (route[i].equals(rideRequest.getLocation())) {
                    allow = true;
                    break;
                }
            }
            if(allow){
                for (int i = route.length - 1; i > -1; i--) {
                    if (!route[i].equals(rideRequest.getLocation())) {
                        matchLocations.add(route[i]);
                    } else {
                        matchLocations.add(route[i]);
                        break;
                    }
                }
                break;
            }
        }
        return matchLocations;
    }

}