package com.example.merge;

import android.app.Application;
import android.content.Context;

import com.example.merge.login.AutoLogin;
import com.example.merge.network.RetrofitInstanceFactory;
import com.example.merge.untility.GoogleTranslate;

public class ThisApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RetrofitInstanceFactory.setApplication(this);
        GoogleTranslate.initialize(this);
        AutoLogin.initialize(this);
    }

    public static Context getContext(){
        return mContext;
    }
}
