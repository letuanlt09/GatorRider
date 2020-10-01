package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Users;
import com.gatorRider.GatorRider.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements org.hibernate.service.Service {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
