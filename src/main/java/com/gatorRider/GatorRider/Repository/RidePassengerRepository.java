package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.RidePassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public  interface RidePassengerRepository extends JpaRepository<RidePassenger,String>{
    List<RidePassenger> findByPassengerId(String passengerId);
    List<RidePassenger> findByRideId(String rideId);
    RidePassenger findOneByRideId(String rideId);
}
