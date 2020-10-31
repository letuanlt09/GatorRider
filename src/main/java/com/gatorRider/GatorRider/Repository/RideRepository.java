package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, String> {
    List<Ride> findByDriverId(String driverId);
}
