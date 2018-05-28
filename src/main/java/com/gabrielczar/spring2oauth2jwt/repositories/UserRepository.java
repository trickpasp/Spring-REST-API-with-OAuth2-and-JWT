package com.gabrielczar.spring2oauth2jwt.repositories;


import com.gabrielczar.spring2oauth2jwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findByUsername(String username);
}
