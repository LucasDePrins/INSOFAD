package com.example.powerflex.services;

import com.example.powerflex.dao.UserRepository;
import com.example.powerflex.models.CustomUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userDAO;


    public UserService(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser customUser = userDAO.findByEmail(email);

        return new User(
                email,
                customUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
