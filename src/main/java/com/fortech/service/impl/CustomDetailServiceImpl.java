package com.fortech.service.impl;

import com.fortech.entity.Role;
import com.fortech.entity.User;
import com.fortech.repository.RoleRepository;
import com.fortech.repository.UserRepository;
import com.fortech.service.CustomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomDetailServiceImpl implements UserDetailsService, CustomDetailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("username not found");
        }

        List<String> roleStringList = new ArrayList<>();

        List<Role> roles = roleRepository.findByUserId(user.getUserId());

        if (roles.isEmpty()) {
            roleStringList.add("USER");
        } else {
            for (Role role : roles) {
                roleStringList.add(role.getRole());
            }
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleStringList.stream().collect(Collectors.joining()))
                .build();


    }
}
