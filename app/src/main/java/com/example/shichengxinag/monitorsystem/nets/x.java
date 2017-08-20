package com.example.shichengxinag.monitorsystem.nets;

import com.example.shichengxinag.monitorsystem.BuildConfig;
import com.example.shichengxinag.monitorsystem.entities.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by shichengxinag on 2017/6/19.
 */

public class x {
    private static Retrofit mRetrofit;

    public static Api http() {
        if (mRetrofit == null)
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.base_url)
                    .addConverterFactory(new MyConverterFactory())
                    .build();
        return mRetrofit.create(Api.class);
    }

    public interface Api {
        @GET("/account/first.htm")
        Call<Result<String>> login(@HeaderMap Map<String,String> heads,@QueryMap Map<String, String> params);//登录
        @GET("/account/map.json")
        Call<Result> index(@HeaderMap Map<String,String> heads, @QueryMap Map<String,String>  params);//首页
    }
}
