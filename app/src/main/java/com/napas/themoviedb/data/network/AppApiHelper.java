package com.napas.themoviedb.data.network;

import com.google.gson.reflect.TypeToken;
import com.napas.themoviedb.BuildConfig;
import com.napas.themoviedb.data.model.BaseResponse;
import com.napas.themoviedb.data.model.Movie;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    private static final String QUERY_PARAM_API_KEY = "api_key";
    private static final String QUERY_PARAM_PAGE = "page";
    private static final String PATH_PARAM_MOVIE_ID = "movie_id";

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<BaseResponse<Movie>> getNowPlaying(Integer page) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_NOW_PLAYING)
                .addQueryParameter(QUERY_PARAM_API_KEY, BuildConfig.API_KEY)
                .addQueryParameter(QUERY_PARAM_PAGE, String.valueOf(page))
                .build()
                .getParseObservable(new TypeToken<BaseResponse<Movie>>() {
                });
    }

    @Override
    public Observable<BaseResponse<Movie>> getSimilarMovies(Integer movieId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_SIMILAR_MOVIES)
                .addPathParameter(PATH_PARAM_MOVIE_ID, String.valueOf(movieId))
                .addQueryParameter(QUERY_PARAM_API_KEY, BuildConfig.API_KEY)
                .build()
                .getParseObservable(new TypeToken<BaseResponse<Movie>>() {
                });
    }
}

