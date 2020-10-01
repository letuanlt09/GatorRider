package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.data.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements org.hibernate.service.Service {
    @Autowired
    private DriverRepository driverRepository;

    public Optional<Driver> Authorize(AuthPair authPair) {
        Driver result = driverRepository.findByEmail(authPair.getEmail());
        if (result.getPasswordHash().equals(authPair.getPassword())) {
            return Optional.of(result);
        }
        else return Optional.empty();
    }

}
