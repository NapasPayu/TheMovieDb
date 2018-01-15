package com.napas.themoviedb;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.napas.themoviedb.di.component.ApplicationComponent;
import com.napas.themoviedb.di.component.DaggerApplicationComponent;
import com.napas.themoviedb.di.module.ApplicationModule;

import okhttp3.OkHttpClient;

public class TheMovieDbApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

        initAndroidNetworking();
        initStetho();
    }

    private void initAndroidNetworking() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}