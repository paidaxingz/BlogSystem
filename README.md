# BlogSystem
博客系统


目录结构
    |-- BlogManagerSystem
    |-- src
        |   |-- main
        |   |   |-- docker
        |   |   |   |-- Dockerfile   用来构建镜像的dockerfile
        |   |   |-- java
        |   |   |   |-- com
        |   |   |       |-- example
        |   |   |           |-- blogmanagersystem
        |   |   |               |-- BlogManagerSystemApplication.java  启动类
        |   |   |               |-- Config  配置
        |   |   |               |   |-- JwtAuthenticationFilter.java  jwt过滤器
        |   |   |               |   |-- MyBatisConfig.java      MyBatis配置
        |   |   |               |   |-- SecurityConfig.java     SpringSecurity配置
        |   |   |               |-- Controller
        |   |   |               |   |-- LoginController.java
        |   |   |               |   |-- PostController.java
        |   |   |               |   |-- UserController.java
        |   |   |               |-- Mapper
        |   |   |               |   |-- PostMapper.java
        |   |   |               |   |-- UserMapper.java
        |   |   |               |-- POJO
        |   |   |               |   |-- Post.java
        |   |   |               |   |-- Result.java
        |   |   |               |   |-- User.java
        |   |   |               |-- Service
        |   |   |               |   |-- IPostService.java
        |   |   |               |   |-- IUserService.java
        |   |   |               |   |-- Impl
        |   |   |               |       |-- PostService.java
        |   |   |               |       |-- UserService.java
        |   |   |               |-- Utils
        |   |   |                   |-- JwtTokenUtil.java    jwt工具类
        |   |   |                   |-- KeyGenerator.java    密钥生成
        |   |   |-- resources
        |   |       |-- application.yml   配置文件



数据库DML语句
create database blog_v1;
use blog_v1;
CREATE TABLE sys_user (
user_id SERIAL PRIMARY KEY COMMENT '用户ID',
username VARCHAR(100) NOT NULL UNIQUE COMMENT '用户名',
password VARCHAR(255) NOT NULL COMMENT '密码',
email VARCHAR(255) NOT NULL UNIQUE COMMENT '邮箱',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
last_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '系统用户表';



CREATE TABLE post (
post_id SERIAL PRIMARY KEY COMMENT '文章ID',
title VARCHAR(255) NOT NULL COMMENT '文章标题',
content TEXT NOT NULL COMMENT '文章内容',
user_id INT NOT NULL COMMENT '作者ID',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章创建时间',
last_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章最后修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '文章表';


功能需求
1.登录认证
使用SpringSecurity配合JWT进行,“\login”请求会将上传用户名和密码,查询数据库是否存在，根据用户名生成token，有效时间60分钟。
其他请求，请求头需要携带token,自定义jwt拦截器，取出请求头中token进行认证
2.- GET /api/posts?uid= - 获取某个用户的所有文章列表 支持分页/按创建时间正/倒叙
使用pagehelper插件进行分页处理，排序则需要在请求参数中具体说明采用DESC或者ASC策略
3.- DELETE /api/posts/{id} - 删除文章（需要登录和权限判断）
删除前进行权限校验，根据请求的token拿到用户名，去数据库查询userId和文章的userId对比，判断是否有删除权限。

       