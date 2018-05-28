package com.gabrielczar.spring2oauth2jwt.handlers;

import com.gabrielczar.spring2oauth2jwt.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@Component
@Transactional
public class UserListenerHandler {
    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @PrePersist
    public void preSave(final User user) {
        LOGGER.info("INIT LISTENER HANDLER");

        user.setPassword(new BCryptPasswordEncoder()
                .encode(user.getPassword()));
    }

}
