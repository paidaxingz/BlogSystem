package com.example.blogmanagersystem.Service.Impl;


import com.example.blogmanagersystem.Mapper.UserMapper;
import com.example.blogmanagersystem.POJO.User;
import com.example.blogmanagersystem.Service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    public void createUser(User user) {
        // 对用户密码进行加密
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword); // 替换用户实体中的密码为加密后的密码
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public int containUser(String username, String password) {
        return userMapper.containUser(username,password);
    }
}

