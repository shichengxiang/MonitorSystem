package com.example.shichengxinag.monitorsystem.nets;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson mGson;
    private final Type mType;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            ResultResponse resultResponse = mGson.fromJson(response, ResultResponse.class);
            if (resultResponse.getSuccess()) {
                return mGson.fromJson(resultResponse.getResult(), mType);
            } else {

            }
        } finally {

        }

        return null;
    }
}
