package com.example.confirmdemo.init;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 25290
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int state;
    private String message;
    private Object data;
}
