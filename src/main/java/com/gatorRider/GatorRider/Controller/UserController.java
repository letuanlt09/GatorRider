package com.gatorRider.GatorRider.Controller;

import com.gatorRider.GatorRider.Model.Users;
import com.gatorRider.GatorRider.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UsersService usersService;
    
    @GetMapping("/list")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }
    @GetMapping("/test")
    public String test() {
        return "Hello I am here";
    }
}
