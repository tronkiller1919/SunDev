package com.returnorder.managment.authorizationservice.repository;

import com.returnorder.managment.authorizationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

}
