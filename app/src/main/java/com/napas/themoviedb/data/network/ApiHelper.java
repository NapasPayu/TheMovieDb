package com.napas.themoviedb.data.network;

import com.napas.themoviedb.data.model.BaseResponse;
import com.napas.themoviedb.data.model.Movie;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<BaseResponse<Movie>> getNowPlaying(Integer page);

    Observable<BaseResponse<Movie>> getSimilarMovies(Integer movieId);

}
