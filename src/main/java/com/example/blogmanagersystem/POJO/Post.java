package com.example.blogmanagersystem.POJO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int PostId;
    private String Title;
    private String Content;
    private int UserId;
    private Date Created;
    private Date LastModified;

}
