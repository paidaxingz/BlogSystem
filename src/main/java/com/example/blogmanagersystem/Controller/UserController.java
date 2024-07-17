package com.example.blogmanagersystem.Controller;


import com.example.blogmanagersystem.POJO.User;

import com.example.blogmanagersystem.Service.IUserService;
import com.example.blogmanagersystem.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        userService.deleteUser(userId);
    }


}
