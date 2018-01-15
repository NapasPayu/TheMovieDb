package com.napas.themoviedb.di.module;

import android.app.Application;
import android.content.Context;

import com.napas.themoviedb.data.AppDataManager;
import com.napas.themoviedb.data.DataManager;
import com.napas.themoviedb.data.network.ApiHelper;
import com.napas.themoviedb.data.network.AppApiHelper;
import com.napas.themoviedb.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

}
