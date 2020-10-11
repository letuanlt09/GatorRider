package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/ride")
public class RideController {
    @Autowired
    RideService rideService;
    @GetMapping("/list")
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }
    @PostMapping("/createRide")
    public void createRide(@RequestBody RideRequest rideRequest) {
        rideService.createRide(rideRequest);
    }
    @GetMapping("/retrieveRide")
    public List<Ride> retrieveMyRide(@RequestParam Long driverId){
        return rideService.getMyRide(driverId) ;
    }
    @PostMapping("/updateRide")
    public void updateRide(@RequestBody RideRequest rideRequest){
        rideService.updateRide(rideRequest);
    }
    @GetMapping("/deleteRide")
    public void deleteRide(@RequestParam Long rideId){
        rideService.deleteRide(rideId) ;
    }
}
