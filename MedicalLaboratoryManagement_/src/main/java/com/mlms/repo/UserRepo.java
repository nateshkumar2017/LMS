package com.mlms.repo;

import com.mlms.entities.TestResult;
import com.mlms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
