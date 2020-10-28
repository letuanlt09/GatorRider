package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {
    List<Driver> findAll();
    Driver save(Driver driver);
    Driver findByEmail(String email);
    Optional<Driver> findById(String id);
    Driver findByPhone(String phone);
}
