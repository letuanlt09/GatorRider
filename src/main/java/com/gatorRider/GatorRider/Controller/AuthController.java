package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Service.AuthService;
import com.gatorRider.GatorRider.data.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@CrossOrigin
@RestController
@RequestMapping(value="/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/driver")
    public ResponseEntity<String> Login(@RequestBody AuthPair authPair) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return authService.Authorize(authPair).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(authService.Authorize(authPair).get().getId()) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: Check your email and password");
    }
}