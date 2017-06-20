package com.example.shichengxinag.monitorsystem.nets;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by shichengxinag on 2017/6/19.
 */

public class x {
    private static Retrofit mRetrofit;

    public static Api http(){
        if(mRetrofit==null)
            mRetrofit=new Retrofit.Builder()
                    .baseUrl("")
                    .build();
        return mRetrofit.create(Api.class);
    }
    public interface Api{
        @GET("/account/first.htm")
        Call<String> login(@QueryMap Map<String,String> params);
    }
}
