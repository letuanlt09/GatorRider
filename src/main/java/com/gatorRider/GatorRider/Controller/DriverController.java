package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/test")
    public String test() {
        return "Hello I am here";
    }
}
