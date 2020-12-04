package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RidePassenger;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Service.MatchService;
import com.gatorRider.GatorRider.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/ride")
public class RideController {
    @Autowired
    RideService rideService;

    @Autowired
    MatchService matchService;

    @GetMapping("/listAllPassRide")
    public List<RidePassenger> getAllPassRide() {
        return rideService.getAllPassRide();
    }
    @GetMapping("/listMyRideAsPass/{driverId}")
    public List<Ride> getMyRideAsPass(@PathVariable String driverId) {
        return rideService.getMyRideAsPass(driverId);
    }
    @GetMapping("/listPassOfRide/{rideId}")
    public List<Driver> getPassOfRide(@PathVariable String rideId) {
        return rideService.getPassOfRide(rideId);
    }
    @GetMapping("/list")
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }
    @PostMapping("/createRide")
    public ResponseEntity<String> createRide(@RequestBody RideRequest rideRequest) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.createRide(rideRequest));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/retrieveRide/{driverId}")
    public List<Ride> retrieveMyRide(@PathVariable String driverId){
        return rideService.getMyRide(driverId) ;
    }
    @PostMapping("/updateRide")
    public ResponseEntity<String> updateRide(@RequestBody RideRequest rideRequest){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.updateRide(rideRequest));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteRide/{rideId}")
    public void deleteRide(@PathVariable String rideId){
        rideService.deleteRide(rideId) ;
    }
    @DeleteMapping("/deleteReservation")
    public void deleteReservation(@RequestBody RidePassenger ridePassenger){
        rideService.deleteReservation(ridePassenger); ;
    }

    @GetMapping("/matchRide")
    public ResponseEntity<List<Ride>> matchRide(RideRequest rideRequest) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(matchService.findMatchRide(rideRequest));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getAllLocation(RideRequest rideRequest) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(matchService.getLocations());
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }


    @PostMapping("/reserveSeat")
    public ResponseEntity<String> updateRide(@RequestBody RidePassenger ridePassenger){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.addPassenger(ridePassenger));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


}
