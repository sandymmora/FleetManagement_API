package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.dto.UserDTO;
import com.fleetmanagement.api_rest.model.RegisterRequest;
import com.fleetmanagement.api_rest.model.UserModel;
import com.fleetmanagement.api_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserDTO saveUser(@RequestBody UserModel user){
        return userService.addUser(user);
    }

    @GetMapping()
    public List<UserDTO> getUsers(@RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit){
        return userService.getUsers(page,limit);
    }
    @PatchMapping("/{id}")
    public UserDTO updateNameUser(@PathVariable Integer id, @RequestBody UserModel userUpdate){
        return userService.updateNameUser(id, userUpdate);
    }
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
