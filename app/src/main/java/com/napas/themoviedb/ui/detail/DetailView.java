package com.napas.themoviedb.ui.detail;

import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.ui.base.MvpView;

import java.util.List;

public interface DetailView extends MvpView {

    void initActionBarTitle(String title);

    void initMovieDetail(Movie movie);

    void initSimilarMovies(List<Movie> similarMovies);

}
