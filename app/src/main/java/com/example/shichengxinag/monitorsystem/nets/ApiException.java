package com.example.shichengxinag.monitorsystem.nets;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class ApiException extends Exception {
    private final int code;
    private String displayMessage;
    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;

    public ApiException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage + ",code=" + code;
    }
}
