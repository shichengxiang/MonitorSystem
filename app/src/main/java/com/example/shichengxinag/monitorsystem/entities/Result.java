package com.example.shichengxinag.monitorsystem.entities;

/**
 * Created by shichengxinag on 2017/6/21.
 */

public class Result<T> {
    String code;
    String msg;
    T data;

    public boolean isValid(){
        return code!=null && code.equals("0");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
