package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserModel> findAllUsersPag(Pageable pageable);

    List<UserModel> findByEmail(String email);
}
