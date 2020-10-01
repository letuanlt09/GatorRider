package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverService implements org.hibernate.service.Service {
    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    @Transactional
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
