package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService implements org.hibernate.service.Service {
    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
}
