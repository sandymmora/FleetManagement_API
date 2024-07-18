package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.UserDTO;
import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.exception.EmailAlreadyExist;
import com.fleetmanagement.api_rest.mapper.UserDTOMapper;
import com.fleetmanagement.api_rest.model.RegisterRequest;
import com.fleetmanagement.api_rest.model.Role;
import com.fleetmanagement.api_rest.model.UserModel;
import com.fleetmanagement.api_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO addUser(UserModel userModel){
        var user = UserModel.builder()
                .name(userModel.getName())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .role(Role.USER)
                .build();

        if(userModel.getEmail() == null || userModel.getPassword() == null){
            throw new BadRequestException("Email and Password are Required");
        }
        if(!userRepository.findByEmail(userModel.getEmail()).isEmpty()){
            System.out.println(userRepository.findByEmail(userModel.getEmail()));
            throw new EmailAlreadyExist("Email already exists");
        }
        UserModel userSaved = userRepository.save(user);

        return userDTOMapper.apply(userSaved);
    }
    public List<UserDTO> getUsers(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page, limit);
        return userRepository.findAllUsersPag(pageable)
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }
    public UserDTO updateNameUser(Integer id, UserModel userUpdate){
        Optional<UserModel> optionalUserModel = userRepository.findById(id);
        UserModel actualUser = optionalUserModel.get();
        actualUser.setName(userUpdate.getName());
        UserModel user = userRepository.save(actualUser);

        return userDTOMapper.apply(user);
    }
    public UserDTO deleteUser(Integer id){
        Optional<UserModel> optionalUserModel = userRepository.findById(id);
        UserModel userExist = optionalUserModel.get();

        return userDTOMapper.apply(userExist);
    }
}
