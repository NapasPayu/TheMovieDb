package com.napas.themoviedb.di.module;

import android.app.Activity;
import android.content.Context;

import com.napas.themoviedb.di.ActivityContext;
import com.napas.themoviedb.di.PerActivity;
import com.napas.themoviedb.ui.detail.DetailPresenter;
import com.napas.themoviedb.ui.detail.DetailPresenterImpl;
import com.napas.themoviedb.ui.detail.DetailView;
import com.napas.themoviedb.ui.main.MainPresenter;
import com.napas.themoviedb.ui.main.MainPresenterImpl;
import com.napas.themoviedb.ui.main.MainView;
import com.napas.themoviedb.util.rx.AppSchedulerProvider;
import com.napas.themoviedb.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(MainPresenterImpl<MainView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailPresenter<DetailView> provideDetailPresenter(DetailPresenterImpl<DetailView> presenter) {
        return presenter;
    }
}
