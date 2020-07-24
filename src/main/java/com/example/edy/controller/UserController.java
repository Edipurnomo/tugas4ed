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

//    @GetMapping("")
//    List<User> geAllUser() {
//        return userServices.getAllUser();
//    }

//    @GetMapping("getByUsername")
//    User findByUsername(@RequestParam String username) {
//        User result = service.getAllUser(username);
//        return result;
//    }

    @GetMapping
    public List<User> getAllUser(@RequestParam(value ="pageNo", defaultValue = "0") Integer pageNo,
                                 @RequestParam(value = "sortKey", defaultValue = "name") String sortKey)
    {
        return userServices.getAllUser(pageNo, sortKey);
    }

    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody User body) {
        return userServices.insertNewUser(body);

    }


    @GetMapping("/ByAddress")
    public List<User>getUsersByAddress(
            @RequestParam(required = false)String search,
            @RequestParam(required = false)String type )
    { return userServices.getAllUserByAddress(search,type); }


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
    Map<String, Object> UpdateUser(@RequestBody User body){
        Map<String, Object> result = new HashMap<>();
        if (userServices.updateUser(body)) {
            result.put("success", true);
            result.put("mes", "berhasil");
        }else {
            result.put("success", false);
            result.put("mes", "gagal");
        }
        return result;
    }


}
