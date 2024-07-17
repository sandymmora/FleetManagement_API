//package com.fleetmanagement.api_rest.service;
//
//import com.fleetmanagement.api_rest.dto.UserDTO;
//import com.fleetmanagement.api_rest.model.UserModel;
//import com.fleetmanagement.api_rest.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void testGetUsers() {
//        List<UserModel> userModelList = new ArrayList<>();
//        userModelList.add(new UserModel(5,"Sol", "sol@mail.com", "15498"));
//        userModelList.add(new UserModel(6,"Ernesto", "mote@mail.com", "15498"));
//        userModelList.add(new UserModel(7,"Patricia", "opa@mail.com", "15498"));
//        Pageable pageable = PageRequest.of(0,3);
//        Mockito.when(userRepository.findAllUsersPag(pageable)).thenReturn(userModelList);
//        List<UserDTO> usersFound = userService.getUsers(0,3);
//        assertEquals(usersFound.size(),3);
//    }
//}
