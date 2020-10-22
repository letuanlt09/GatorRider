package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, String> {
}
