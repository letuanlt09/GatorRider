package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, String> {
    List<Ride> findByDestinationInAndDateBetween(List<String> destinations, Date from, Date to);
    List<Ride> findByDriverId(String driverId);
}
