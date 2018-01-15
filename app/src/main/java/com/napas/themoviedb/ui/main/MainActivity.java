package com.napas.themoviedb.ui.main;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.napas.themoviedb.R;
import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.ui.base.BaseActivity;
import com.napas.themoviedb.ui.custom.AutofitRecyclerView;
import com.napas.themoviedb.ui.detail.DetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    MainPresenter<MainView> mPresenter;

    @BindView(R.id.rv_movies)
    AutofitRecyclerView rvMovies;

    private MovieListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        mPresenter.onInitNowPlayingData();
    }

    @Override
    public void initMovies(List<Movie> movies) {
        mAdapter = new MovieListAdapter(this, movies);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this, rvMovies);
        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(mAdapter);
    }

    @Override
    public void addMovies(List<Movie> movies) {
        mAdapter.addData(movies);
    }

    @Override
    public void hideLoadMore() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void endLoadMore() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void showFailedLoadMore() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void startDetailActivity(Movie movie) {
        startActivity(DetailActivity.getStartIntent(this, movie));
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.onLoadMoreNowPlayingData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Movie clickedMovie = mAdapter.getItem(position);
        startDetailActivity(clickedMovie);
    }
}
