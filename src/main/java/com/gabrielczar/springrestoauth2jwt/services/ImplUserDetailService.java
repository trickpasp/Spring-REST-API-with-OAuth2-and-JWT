package com.gabrielczar.springrestoauth2jwt.services;

import com.gabrielczar.springrestoauth2jwt.domain.User;
import com.gabrielczar.springrestoauth2jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ImplUserDetailService implements UserDetailsService {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private final UserRepository userRepository;

    @Autowired
    public ImplUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            LOGGER.info("User not found!");
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        LOGGER.info(user.toString());
        return (UserDetails) user;
    }
}
