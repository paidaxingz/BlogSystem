package com.example.blogmanagersystem.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    int code;
    Object data;
    String message;

    public static Result success(Object data) {
        return new Result(200, data, "Success");
    }

    public static Result failure(int code, String message) {
        return new Result(code, null, message);
    }
}
