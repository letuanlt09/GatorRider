package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Driver createNewDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

}
