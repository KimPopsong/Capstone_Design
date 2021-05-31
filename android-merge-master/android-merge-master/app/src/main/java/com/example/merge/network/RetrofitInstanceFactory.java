package com.example.merge.network;

import android.app.Application;
import android.content.Context;

import com.example.merge.Config;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 레트로핏 인스턴스를 만들어서 가져와주는 클래스입니다.
 */
public class RetrofitInstanceFactory {
    private static MergeNetworkService instance;
    private static Application app;

    public static void setApplication(Application app) {
        RetrofitInstanceFactory.app = app;
    }

    public static MergeNetworkService getInstance() {
        // 싱글턴입네다.
        if (instance == null) {
            createInstance();
        }

        return instance;
    }

    private static void createInstance() {
        if (app == null) {
            throw new RuntimeException("Must call setApplication first");
        }

        // 쿠키 저장하고 꺼내오는 일 대신 해 주는 친구입니다.
        ClearableCookieJar cookieJar = new PersistentCookieJar(
                new SetCookieCache(),
                new SharedPrefsCookiePersistor(app.getApplicationContext())
        );

        instance = new Retrofit.Builder()
                .baseUrl(Config.serverBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().cookieJar(cookieJar).build())
                .build()
                .create(MergeNetworkService.class);
    }
}
