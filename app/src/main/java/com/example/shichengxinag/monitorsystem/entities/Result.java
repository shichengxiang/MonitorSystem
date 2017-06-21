package com.example.shichengxinag.monitorsystem.entities;

/**
 * Created by shichengxinag on 2017/6/21.
 */

public class Result<T> {
    int code;
    String result;
    T message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
