package com.example.blogmanagersystem.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int UserId;
    private String UserName;
    private String Password;
    private String Email;
    private Date Created;
    private Date LastModified;
}
