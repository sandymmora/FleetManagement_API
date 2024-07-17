//package com.fleetmanagement.api_rest.repository;
//
//import com.fleetmanagement.api_rest.model.UserModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UserRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @BeforeEach
//    void setUp(){
//        UserModel user1 = new UserModel(1,"Melissa", "meli@mail.com", "457");
//        UserModel user2 = new UserModel(2,"Teresa", "tere@mail.com", "457");
//        UserModel user3 = new UserModel(3,"Raul", "raul@mail.com", "457");
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//    }
//    @Test
//    public void testFindAllUsersPag(){
//        Pageable pageable = PageRequest.of(0,3);
//        List<UserModel> users = userRepository.findAllUsersPag(pageable);
//        assertThat(users).hasSize(3);
//        assertThat(users).isNotEmpty();
//    }
//    @Test
//    public void testFindByEmail(){
//        String email = "raul@mail.com";
//        Optional<UserModel> userFound = userRepository.findByEmail(email);
//        assertThat(userFound).isPresent();
//        assertEquals(userFound.get().getEmail(), email);
//    }
//}
