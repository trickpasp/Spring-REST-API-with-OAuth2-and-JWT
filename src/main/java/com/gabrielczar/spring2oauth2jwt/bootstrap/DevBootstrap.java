package com.gabrielczar.spring2oauth2jwt.bootstrap;


import com.gabrielczar.spring2oauth2jwt.entities.Authority;
import com.gabrielczar.spring2oauth2jwt.entities.User;
import com.gabrielczar.spring2oauth2jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

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

        userRepository.saveAll(Arrays.asList(adm, user));
    }
}