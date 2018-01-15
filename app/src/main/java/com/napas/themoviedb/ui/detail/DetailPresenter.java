package com.napas.themoviedb.ui.detail;

import com.napas.themoviedb.di.PerActivity;
import com.napas.themoviedb.ui.base.MvpPresenter;

@PerActivity
public interface DetailPresenter<V extends DetailView> extends MvpPresenter<V> {

    void onInitSimilarMoviesData(Integer movieId);

}
