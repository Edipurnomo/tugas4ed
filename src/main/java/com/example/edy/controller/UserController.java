package com.example.edy.controller;


import com.example.edy.model.User;
import com.example.edy.repository.UserRepository;
import com.example.edy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

        @Autowired
    UserRepository repository;
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




    @DeleteMapping("/delete")
    Map<String, Object> deleteUsers(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (userServices.deleteByUserId(id)) {
            result.put("succes", true);
        } else {
            result.put("succes", false);
        }
        return result;
    }

    @PutMapping("/update")
   public Map Updateuser(@RequestBody User body) {
       // User result = repository.findById(i);
        Map<String, Object> result1 = new HashMap<>();
        if (userServices.updateData(body)) {
            result1.put("succes", true);
            result1.put("message", "berhasil");
        } else {
            result1.put("succes", false);
            result1.put("message", "gagal");
        }
        return result1;
 }


}
