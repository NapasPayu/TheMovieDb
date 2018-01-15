package com.napas.themoviedb.data;

import android.content.Context;

import com.napas.themoviedb.data.model.BaseResponse;
import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.data.network.ApiHelper;
import com.napas.themoviedb.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<BaseResponse<Movie>> getNowPlaying(Integer page) {
        return mApiHelper.getNowPlaying(page);
    }

    @Override
    public Observable<BaseResponse<Movie>> getSimilarMovies(Integer movieId) {
        return mApiHelper.getSimilarMovies(movieId);
    }
}
