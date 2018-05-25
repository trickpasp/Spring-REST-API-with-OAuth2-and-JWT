package com.gabrielczar.springrestoauth2jwt.repositories;

import com.gabrielczar.springrestoauth2jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findByUsername(String username);
}
