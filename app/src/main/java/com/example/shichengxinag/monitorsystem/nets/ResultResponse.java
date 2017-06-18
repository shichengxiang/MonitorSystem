package com.example.shichengxinag.monitorsystem.nets;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class ResultResponse {
    private int code;
    private String result;

    public boolean getSuccess() {
        if (code == 0)
            return true;
        return false;
    }

    public String getResult() {
        return result;
    }
}
