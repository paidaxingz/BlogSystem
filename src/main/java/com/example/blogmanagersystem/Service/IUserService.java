package com.example.blogmanagersystem.Service;


import com.example.blogmanagersystem.POJO.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int userId);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);

    int containUser(String username, String password);
}
