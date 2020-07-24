package com.example.edy.controller;


import com.example.edy.model.Address;
import com.example.edy.model.User;
import com.example.edy.repository.AddressRepository;
import com.example.edy.repository.UserRepository;
import com.example.edy.service.AddressService;
import com.example.edy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//
@RestController
@RequestMapping("/api/address")
public class AddressControl {

    //    @Autowired
//    UserRepository repository;
    @Autowired
    UserService userServices;

    @GetMapping("")
    List<User> geAllUser() {
        return userServices.getAllUser();
    }

//    @GetMapping("getByUsername")
//    User findByUsername(@RequestParam String username) {
//        User result = service.getAllUser(username);
//        return result;
//    }

    @PostMapping("/insert")

    public Map<String, Object> insertUser(@RequestBody User body) {
        return userServices.insertNewUser(body);

    }
}
