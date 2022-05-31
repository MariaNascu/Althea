package com.fortech.service.impl;

import com.fortech.dtos.UserResponse;
import com.fortech.entity.Role;
import com.fortech.entity.User;
import com.fortech.exceptions.FiledIsMandatoryException;
import com.fortech.exceptions.UserNotFoundException;
import com.fortech.repository.RoleRepository;
import com.fortech.repository.UserRepository;
import com.fortech.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserResponse findById(Integer userId) {
        UserResponse userResponse = new UserResponse();
        User user = userRepository.findByUserId(userId);

        userResponse.setId(user.getUserId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());

        return userResponse;
    }

    @Override
    public User createUser(User user) {
        validateUser(user);

        long count = userRepository.countByEmail(user.getEmail());
        if (count > 0) {
            throw new RuntimeException("Email already in used");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void validateUser(User user) {
        if (Strings.isEmpty(user.getFirstName())) {
            throw new FiledIsMandatoryException("First name is mandatory");
        }

        if (Strings.isEmpty(user.getLastName())) {
            throw new FiledIsMandatoryException("Last name is mandatory");
        }

        if (Strings.isEmpty(user.getPhone())) {
            throw new FiledIsMandatoryException("Phone number is mandatory");
        }

        if (Strings.isEmpty(user.getEmail())) {
            throw new FiledIsMandatoryException("Email is mandatory");
        }


    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Integer userId, User user) {

        User newUser = new User();

        newUser.setUserId(newUser.getUserId());
        newUser.setPassword(newUser.getPassword());
        newUser.setFirstName(newUser.getFirstName());
        newUser.setLastName(newUser.getLastName());
        newUser.setEmail(newUser.getEmail());
        newUser.setPhone(newUser.getPhone());
        return userRepository.save(newUser);

    }

    @Override
    public void promote(Integer userId) {
        User newUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        Role role = new Role();
        role.setUserId(newUser.getUserId());
        role.setRole("ADMIN");
        roleRepository.save(role);

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        try {
            userRepository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            System.out.println("Cannot find username: " + username);
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        try {
            userRepository.findByEmail(email);
        } catch (UsernameNotFoundException e) {
            System.out.println("Cannot find email: " + email);
        }
        return userRepository.findByUsername(email);
    }

    @Override
    public User findByPhone(String phone) {
        try {
            userRepository.findByPhone(phone);
        } catch (UsernameNotFoundException e) {
            System.out.println("Cannot find email: " + phone);
        }
        return userRepository.findByUsername(phone);
    }
}

