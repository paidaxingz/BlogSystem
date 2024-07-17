package com.example.blogmanagersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogManagerSystemApplication {

	public static void main(String[] args) {
		System.out.println("------------系统启动-----------");
		SpringApplication.run(BlogManagerSystemApplication.class, args);

	}

}
