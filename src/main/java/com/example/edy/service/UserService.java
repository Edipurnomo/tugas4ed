package com.example.edy.service;


import com.example.edy.model.Address;
import com.example.edy.model.User;
import com.example.edy.repository.AddressRepository;
import com.example.edy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getAllUserByAddress(String search, String type) {
        switch (type) {
            case "city":
                return userRepository.findByAddress_CityContaining(search);
            case "province":
                return userRepository.findByAddress_ProvinceContaining(search);
            case "country":
                return userRepository.findByAddress_CountryContaining(search);
            default:
                return null;
        }
    }

    public Map<String, Object> insertNewUser(User body) {
        User result;
        Address address = body.getAddress();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            body.setAddress(null);
            result = userRepository.save(body);
            //insert address
            if (address != null) {
                address.setUser(result);
                addressRepository.save(address);
            }
            resultMap.put("success", true);
            resultMap.put("message", "berhasil insert user");
        } catch (Exception e) {
            resultMap.put("success", false);
            resultMap.put("message", "gagal insert user" + e.getMessage());
        }
        return resultMap;
    }

    public Page<User> getByPage(String search, Pageable pageable) {
        return userRepository.findByUsernameContaining(search, pageable);
    }

//    public Map updateUser(User body) {
//        User userResult = userRepository.findById(body.getId());
//        Map<String, Object> resultMap = new HashMap<>();
//        if (userResult != null) {
//            try {
////                userResult.setAddress(userResult.getAddress());
//                userRepository.save(body);
//                resultMap.put("success", true);
//                resultMap.put("message", "update user sukses");
//            } catch (Exception e) {
//                resultMap.put("success", false);
//                resultMap.put("message", "update user gagal");
//            }
//        } else {
//            resultMap.put("success", false);
//            resultMap.put("message", "update user gagal");
//        }
//        return resultMap;
//    }
public boolean updateUser(User body) {
    User userResult = userRepository.findById(body.getId());

    if (userResult != null) {
        try {
//                userResult.setAddress(userResult.getAddress());
            userRepository.save(body);
            return true;
        } catch (Exception e) {
            return false;
        }
    } else {
        return false;
    }

}

//
//
//    public boolean deleteByUserId(int userId) {
//        User result = userRepository.findById(userId);
//        Map<String, Object> resultMap = new HashMap<>();
//        if (result != null) {
//            try {
//                userRepository.deleteById(userId);
//                resultMap.put("success", true);
//                resultMap.put("message", "user berhasil terhapus");
//            } catch (Exception e) {
//                resultMap.put("success", false);
//                resultMap.put("record", "user gagal terhapus: " + e.getMessage());
//            }
//        } else {
//            resultMap.put("success", false);
//            resultMap.put("record", "address gagal terhapus");
//        }
//        return resultMap;
//    }


    public boolean deleteByUserId(int userId) {
        User result = userRepository.findById(userId);
        if (result != null) {
            try {
                userRepository.delete(result);
                return true;
            } catch (Exception E) {
                return false;
            }
        } else {
            return false;
        }
    }
}