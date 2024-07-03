package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.model.UserModel;
import com.fleetmanagement.api_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel user){
        return userService.addUser(user);
    }
}
