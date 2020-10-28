package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RideService implements org.hibernate.service.Service {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    public String createRide(RideRequest rideRequest) throws Exception {
        rideRequest.validation();
        Ride ride = new Ride();
        ride.setId(UUID.randomUUID().toString());
        ride.setDate(rideRequest.getDate());
        ride.setTime(rideRequest.getTime());
        ride.setDestination(rideRequest.getDestination());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        ride.setDriver(driverRepository.getOne(rideRequest.getDriverId()));
        return rideRepository.save(ride).getId();
    }
    public List<Ride> getMyRide(String driverId){
        List<Ride> allRide = rideRepository.findAll();
        List<Ride> result = new ArrayList<>();
        for(Ride tempRide: allRide){
            if(tempRide.getDriver().getId().equals(driverId)){
                result.add(tempRide);
            }
        }
        return result;
    }
    public String updateRide(RideRequest rideRequest) throws Exception{
        rideRequest.validation();
        Ride ride = rideRepository.getOne(rideRequest.getRideId());
        ride.setDate(rideRequest.getDate());
        ride.setTime(rideRequest.getTime());
        ride.setDestination(rideRequest.getDestination());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        return rideRepository.save(ride).getId();
    }
    public void deleteRide(String rideId){
        rideRepository.deleteById(rideId);
    }

}
