package com.gabrielczar.springrestoauth2jwt.bootstrap;


import com.gabrielczar.springrestoauth2jwt.domain.Authority;
import com.gabrielczar.springrestoauth2jwt.domain.User;
import com.gabrielczar.springrestoauth2jwt.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private UserRepository userRepository;

    @Autowired
    public DevBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.info("INIT APPLICATION CONTEXT");

        userRepository.deleteAll();

        User adm = new User();
        adm.setFirstName("Gabriel");
        adm.setLastName("Czar");
        adm.setUsername("gabrielczar.adm");
        adm.setPassword("123456");
        adm.setAuthorities(Collections.singletonList(new Authority("ROLE_ADMIN")));

        User user = new User();
        user.setFirstName("Gabriel");
        user.setLastName("Czar");
        user.setUsername("gabrielczar");
        user.setPassword("123456");
        user.setAuthorities(Collections.singletonList(new Authority("ROLE_USER")));

        userRepository.save(Arrays.asList(adm, user));
    }
}
