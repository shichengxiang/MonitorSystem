package com.example.shichengxinag.monitorsystem.nets;

import com.example.shichengxinag.monitorsystem.entities.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Result<T>> {
    private final Gson mGson;
    private final Type mType;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public Result<T> convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            return mGson.fromJson(response, mType);
        } finally {

        }
    }
}
