package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RidePassenger;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.Repository.RidePassengerRepository;
import com.gatorRider.GatorRider.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.Date;
import java.util.List;

@Service
public class RideService implements org.hibernate.service.Service {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RidePassengerRepository ridePassengerRepository;
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    public String createRide(RideRequest rideRequest) throws Exception {
        rideRequest.validation();
        Ride ride = new Ride();
        ride.setId(UUID.randomUUID().toString());
        ride.setDateTime(rideRequest.getDateTime());
        ride.setLocation(rideRequest.getLocation());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        ride.setDriver(driverRepository.getOne(rideRequest.getDriverId()));
        ride.setIsOutBound(rideRequest.getIsOutBound());
        return rideRepository.save(ride).getId();
    }
    public List<Ride> getMyRide(String driverId){
        return rideRepository.findByDriverId(driverId);
    }
    public String updateRide(RideRequest rideRequest) throws Exception{
        rideRequest.validation();
        Ride ride = rideRepository.getOne(rideRequest.getRideId());
        ride.setDateTime(rideRequest.getDateTime());
        ride.setLocation(rideRequest.getLocation());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        ride.setIsOutBound(rideRequest.getIsOutBound());
        return rideRepository.save(ride).getId();
    }
    public void deleteRide(String rideId){
        rideRepository.deleteById(rideId);
    }

    @Scheduled(cron="0 0 0 ? * *")
    public void autoDelete(){
        List<Ride> allRide = rideRepository.findAll();
        Date date = new Date();
        for(Ride i: allRide){
            if(date.after(i.getDateTime())){
                rideRepository.delete(i);
            }
        }
    }

    public String addPassenger (RidePassenger ridePassenger) throws Exception{
            ridePassenger.setId(UUID.randomUUID().toString());
            Ride tempRide = rideRepository.getOne(ridePassenger.getRideId());
            if(tempRide.getNumSeatAvailable()>0) {
                ridePassengerRepository.save(ridePassenger).getRideId();
                tempRide.setNumSeatAvailable(tempRide.getNumSeatAvailable() - 1);
                return rideRepository.save(tempRide).getId();
            }
            throw new Exception("No more available seat");
    }

    public List<RidePassenger> getPassRide(String driverId){
        return ridePassengerRepository.findByPassengerId(driverId);
    }
}
