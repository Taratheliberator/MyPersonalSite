package com.mysite.service;

import com.mysite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.mysite.model.User appUser = userRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
    }
}

