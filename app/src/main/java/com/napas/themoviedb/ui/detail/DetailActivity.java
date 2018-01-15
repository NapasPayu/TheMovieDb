package com.napas.themoviedb.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;
import android.widget.TextView;

import com.napas.themoviedb.BuildConfig;
import com.napas.themoviedb.R;
import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.ui.base.BaseActivity;
import com.napas.themoviedb.ui.custom.AutofitRecyclerView;
import com.napas.themoviedb.ui.main.MovieListAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailView {

    @Inject
    DetailPresenter<DetailView> mPresenter;

    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_overview)
    TextView tvOverview;
    @BindView(R.id.rv_similar_movies)
    AutofitRecyclerView rvSimilarMovies;

    private static final String KEY_MOVIE = "movie";
    private MovieListAdapter mAdapter;

    public static Intent getStartIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        Movie movie = getIntent().getParcelableExtra(KEY_MOVIE);
        if (movie != null) {
            initActionBarTitle(movie.getTitle());
            initMovieDetail(movie);
            mPresenter.onInitSimilarMoviesData(movie.getId());
        }
    }

    @Override
    public void initActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void initMovieDetail(Movie movie) {
        Picasso.with(this).load(BuildConfig.POSTER_URL + movie.getPosterPath()).fit().centerCrop().into(ivPoster);
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverview());
    }

    @Override
    public void initSimilarMovies(List<Movie> similarMovies) {
        mAdapter = new MovieListAdapter(this, similarMovies);
        rvSimilarMovies.setFocusable(false);
        rvSimilarMovies.setAdapter(mAdapter);
    }
}
