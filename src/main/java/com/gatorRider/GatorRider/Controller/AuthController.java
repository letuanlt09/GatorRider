package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Trial;
import com.gatorRider.GatorRider.Repository.TrialRepository;
import com.gatorRider.GatorRider.Service.AuthService;
import com.gatorRider.GatorRider.data.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    TrialRepository trialRepository;

    @PostMapping("/driver")
    public String Login(@RequestBody AuthPair authPair) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return authService.Authorize(authPair).isPresent() ?
                authService.Authorize(authPair).get().getId() : null;
    }

    @PostMapping("/try")
    public Trial Hello(@RequestBody Trial trial) {
        return trialRepository.save(trial);
    }

}