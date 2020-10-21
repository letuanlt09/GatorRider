package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
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
    public void createRide(RideRequest rideRequest){
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
        System.out.println(ride);
        rideRepository.save(ride);
    }
    public List<Ride> getMyRide(Long driverId){
        List<Ride> allRide = rideRepository.findAll();
        List<Ride> result = new ArrayList<>();
        for(Ride tempRide: allRide){
            if(tempRide.getDriver().getId().equals(driverId)){
                result.add(tempRide);
            }
        }
        return result;
    }
    public void updateRide(RideRequest rideRequest){
        Ride ride = rideRepository.getOne(rideRequest.getRideId());
        ride.setDate(rideRequest.getDate());
        ride.setTime(rideRequest.getTime());
        ride.setDestination(rideRequest.getDestination());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        rideRepository.save(ride);
    }
    public void deleteRide(Long rideId){
        rideRepository.deleteById(rideId);
    }

}
