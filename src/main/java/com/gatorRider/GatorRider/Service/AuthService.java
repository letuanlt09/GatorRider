package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.data.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService implements org.hibernate.service.Service {
    @Autowired
    private DriverRepository driverRepository;

    public Optional<Driver> Authorize(AuthPair authPair) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Driver result = driverRepository.findByEmail(authPair.getEmail());
        if (result == null) return Optional.empty();
        if (result.getPasswordHash().equals(this.hashPassWord(authPair.getEmail(), authPair.getPassword()))) {
            return Optional.of(result);
        }
        else return Optional.empty();
    }

    public String hashPassWord(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = driverRepository.findByEmail(email).getId().getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 11111, 128);
        return getSecretKeyFactory(salt, spec);
    }

    public String hashPassWord(Driver driver) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = driver.getId().getBytes(StandardCharsets.UTF_8);
        KeySpec spec = new PBEKeySpec(driver.getPasswordHash().toCharArray(), salt, 11111, 128);
        return getSecretKeyFactory(salt, spec);
    }

    private String getSecretKeyFactory(byte[] salt, KeySpec spec) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("salt: %s%n", enc.encodeToString(salt));
        System.out.printf("hash: %s%n", enc.encodeToString(hash));
        return enc.encodeToString(hash);
    }

}
