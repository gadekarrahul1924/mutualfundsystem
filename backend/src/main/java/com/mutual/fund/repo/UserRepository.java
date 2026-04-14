package com.mutual.fund.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mutual.fund.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
