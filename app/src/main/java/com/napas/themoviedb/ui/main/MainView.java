package com.napas.themoviedb.ui.main;

import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.ui.base.MvpView;

import java.util.List;

public interface MainView extends MvpView {

    void initMovies(List<Movie> movies);

    void addMovies(List<Movie> movies);

    void hideLoadMore();

    void endLoadMore();

    void showFailedLoadMore();

    void startDetailActivity(Movie movie);
}
