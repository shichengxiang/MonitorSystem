package com.example.shichengxinag.monitorsystem.nets;

/**
 * Created by shichengxinag on 2017/6/19.
 */

public class BaseException extends Exception {
    private int code;
    private String message;

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
