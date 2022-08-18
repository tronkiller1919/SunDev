package com.returnorder.managment.authorizationservice.service;

import com.returnorder.managment.authorizationservice.entity.User;
import com.returnorder.managment.authorizationservice.model.MyUserDetails;
import com.returnorder.managment.authorizationservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        return new MyUserDetails(user.orElse(null));
    }
}
