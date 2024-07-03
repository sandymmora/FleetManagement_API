package com.fleetmanagement.api_rest.service;


import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.model.UserModel;
import com.fleetmanagement.api_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel addUser(UserModel userModel){
        return userRepository.save(userModel);
    }
}
