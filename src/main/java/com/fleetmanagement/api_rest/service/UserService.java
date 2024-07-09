package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.UserDTO;
import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.exception.EmailAlreadyExist;
import com.fleetmanagement.api_rest.mapper.UserDTOMapper;
import com.fleetmanagement.api_rest.model.UserModel;
import com.fleetmanagement.api_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserDTO addUser(UserModel userModel){
        if(userModel.getEmail() == null || userModel.getPassword() == null){
            throw new BadRequestException("Email and Password are Required");
        }
        if(!userRepository.findByEmail(userModel.getEmail()).isEmpty()){
            System.out.println(userRepository.findByEmail(userModel.getEmail()));
            throw new EmailAlreadyExist("Email already exists");
        }
        UserModel user = userRepository.save(userModel);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());

        return userDTO;
    }
    public List<UserDTO> getUsers(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page, limit);
        return userRepository.findAllUsersPag(pageable)
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }
}
