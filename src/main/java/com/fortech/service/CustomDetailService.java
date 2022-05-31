package com.fortech.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomDetailService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
