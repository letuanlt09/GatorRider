package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
