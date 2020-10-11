package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value="/driver")
public class DriverController {
    @Autowired
    DriverService driverService;
    
    @GetMapping("/list")
    public List<Driver> getAllDriver() {
        return driverService.getAllDriver();
    }

    @PostMapping("/create")
    public String createNewDriver(@RequestBody Driver driver) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return driverService.createDriver(driver);
    }

    @GetMapping("/{id}")
    public Driver GetDriver(@PathVariable String id) {
        return driverService.getOne(id).isPresent() ?
                driverService.getOne(id).get() : null;
    }


}
