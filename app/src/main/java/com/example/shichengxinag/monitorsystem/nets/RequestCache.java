package com.example.shichengxinag.monitorsystem.nets;

import android.content.Context;
import android.text.TextUtils;

import com.example.shichengxinag.monitorsystem.utils.Prefs;

/**
 * Created by shichengxinag on 2017/7/11.
 */

public class RequestCache {
    private static final String REQUESTCACHE="requestcache";

    private static RequestCache instance;
    private Context mContext;
    private RequestCache(Context context){
        this.mContext=context;
    }
    public static RequestCache getInstance(Context context){
        if(instance==null)
            instance=new RequestCache(context);
        return instance;
    }
    private void getRequestCache(){
        String request = Prefs.with(mContext).read(REQUESTCACHE);
        if (TextUtils.isEmpty(request))
            return;

    }
    private void saveRequest(){

    }

}
