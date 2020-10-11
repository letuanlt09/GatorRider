package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createNewDriver(@RequestBody Driver driver) throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(driverService.createDriver(driver));
        } catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> GetDriver(@PathVariable String id) {
        return driverService.getOne(id).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(driverService.getOne(id).get()) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}
