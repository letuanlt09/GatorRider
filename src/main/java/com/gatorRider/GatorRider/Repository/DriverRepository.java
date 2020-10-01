package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAll();
    Driver save(Driver driver);
    Driver findByEmail(String email);
}
