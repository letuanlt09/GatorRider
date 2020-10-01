package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Service.AuthService;
import com.gatorRider.GatorRider.data.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/driver")
    public Driver Login(@RequestBody AuthPair authPair) {
        return authService.Authorize(authPair).isPresent() ?
                authService.Authorize(authPair).get() : null;
    }

}