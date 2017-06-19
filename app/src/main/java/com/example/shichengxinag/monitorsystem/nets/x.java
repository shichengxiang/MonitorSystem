package com.example.shichengxinag.monitorsystem.nets;

import retrofit2.Call;
import retrofit2.Retrofit;

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
        Call<String> login(String name, String pwd);
    }
}
