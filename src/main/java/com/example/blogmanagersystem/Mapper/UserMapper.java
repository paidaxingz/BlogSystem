package com.example.blogmanagersystem.Mapper;


import com.example.blogmanagersystem.POJO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 获取所有用户
    @Select("SELECT * FROM sys_user")
    List<User> getAllUsers();

    // 根据用户ID获取用户
    @Select("SELECT * FROM sys_user WHERE user_id = #{userId}")
    User getUserById(@Param("userId") int userId);

    // 插入用户
    @Insert("INSERT INTO sys_user (username, password, email) " +
            "VALUES (#{username}, #{password}, #{email})")
    void insertUser(User user);

    // 更新用户
    @Update("UPDATE sys_user SET " +
            "username = #{username}, " +
            "password = #{password}, " +
            "email = #{email}, " +
            "last_modified = CURRENT_TIMESTAMP " +
            "WHERE user_id = #{userId}")
    void updateUser(User user);

    // 删除用户
    @Delete("DELETE FROM sys_user WHERE user_id = #{userId}")
    void deleteUser(@Param("userId") int userId);

    @Select("SELECT IFNULL(COUNT(*), 0) FROM sys_user WHERE username = #{username} AND password = #{password}")
    int containUser(String username, String password);

    @Select("SELECT user_id from sys_user WHERE username = #{userName}")
    int getUserByName(String userName);
}
