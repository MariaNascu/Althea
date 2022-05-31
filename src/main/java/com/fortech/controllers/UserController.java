package com.fortech.controllers;

import com.fortech.dtos.UserResponse;
import com.fortech.entity.User;
import com.fortech.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapi")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/getUserById")
    public UserResponse findById(@RequestParam(name = "userId") int userId) {
        return userService.findById(userId);
    }

    @GetMapping("/findAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping ("/findByEmail")
    public User findByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }

    @GetMapping ("/findByUserName")
    public User findByUserName(@RequestParam String username){
        return userService.findByUserName(username);
    }

    @GetMapping ("/findByPhone")
    public User findByPhone(@RequestParam String phone){
        return userService.findByPhone(phone);
    }

    @PutMapping
    public User updateUser(
            @RequestParam(name = "userId") Integer userId,
            @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(name = "userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/promote")
    public void promote(
            @RequestParam(name = "userId") Integer userId) {
        userService.promote(userId);
    }
}