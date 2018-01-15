package com.napas.themoviedb.data.network;

import com.napas.themoviedb.BuildConfig;

public final class ApiEndPoint {

    public static final String GET_NOW_PLAYING = BuildConfig.BASE_URL + "/movie/now_playing";

    public static final String GET_SIMILAR_MOVIES = BuildConfig.BASE_URL + "/movie/{movie_id}/similar";

}
