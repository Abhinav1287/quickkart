package com.kt.quickkart.controller;

import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quickkart")
public class QuickKartController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getusers")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

}
