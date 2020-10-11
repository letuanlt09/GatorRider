package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class DriverService implements org.hibernate.service.Service {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private AuthService authService;

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    @Transactional
    public String createDriver(Driver driver) throws InvalidKeySpecException, NoSuchAlgorithmException {
        driver.setId(UUID.randomUUID().toString());
        driver.setPasswordHash(authService.hashPassWord(driver));
        return driverRepository.save(driver).getId();
    }

    public Optional<Driver> getOne(String id) {
        return driverRepository.findById(id);
    }
}
