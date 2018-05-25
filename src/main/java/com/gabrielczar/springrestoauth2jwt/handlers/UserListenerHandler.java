package com.gabrielczar.springrestoauth2jwt.handlers;

import com.gabrielczar.springrestoauth2jwt.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;

@Component
@Transactional
public class UserListenerHandler {

    @PrePersist
    public void preSave(final User user) {
        user.setPassword(new BCryptPasswordEncoder()
                .encode(user.getPassword()));
    }

}
