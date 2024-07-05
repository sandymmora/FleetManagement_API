package com.fleetmanagement.api_rest.mapper;

import com.fleetmanagement.api_rest.dto.UserDTO;
import com.fleetmanagement.api_rest.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<UserModel, UserDTO> {
    @Override
    public UserDTO apply(UserModel userModel){
        return new UserDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail()
        );
    }

}
