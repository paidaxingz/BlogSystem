package com.example.blogmanagersystem.Controller;

import com.example.blogmanagersystem.POJO.Result;
import com.example.blogmanagersystem.Service.IUserService;
import com.example.blogmanagersystem.Utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@CrossOrigin
@Slf4j
public class LoginController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/login")
    public Object login(@RequestBody String jsonRequest) throws Exception {
        // 使用 Jackson 解析 JSON 请求体
        JsonNode rootNode = objectMapper.readTree(jsonRequest);

        // 直接从 JSON 中取出 username 和 password 字段
        String username = rootNode.get("username").asText();
        String password = rootNode.get("password").asText();
        log.info("用户名：" + username + "密码：" + password + "尝试登录");
        //验证用户名和密码
        int i = userService.containUser(username, password);
        if (i > 0) {
            String token = jwtTokenUtil.generateToken(username);
            log.info("用户名：" + username + "密码：" + password + "登录成功");
            return Result.success(token);
        } else return Result.failure(100001, "用户名或密码错误");
    }



}
