package com.napas.themoviedb.ui.main;

import com.napas.themoviedb.di.PerActivity;
import com.napas.themoviedb.ui.base.MvpPresenter;

@PerActivity
public interface MainPresenter<V extends MainView> extends MvpPresenter<V> {

    void onInitNowPlayingData();

    void onLoadMoreNowPlayingData();
}
