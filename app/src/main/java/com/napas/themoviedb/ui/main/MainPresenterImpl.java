package com.napas.themoviedb.ui.main;

import com.napas.themoviedb.R;
import com.napas.themoviedb.data.DataManager;
import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.data.network.BaseObserver;
import com.napas.themoviedb.ui.base.BasePresenter;
import com.napas.themoviedb.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenterImpl<V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {

    private Integer page = 1;

    @Inject
    public MainPresenterImpl(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onInitNowPlayingData() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().getNowPlaying(page)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new BaseObserver<Movie>() {
                    @Override
                    public void onResponseOK(List<Movie> movies) {
                        getMvpView().hideLoading();
                        getMvpView().initMovies(movies);
                        page++;
                    }

                    @Override
                    public void onResponseError(String errorMessage) {
                        getMvpView().hideLoading();
                        getMvpView().showOkDialog(R.string.error, errorMessage);
                    }
                }));
    }

    @Override
    public void onLoadMoreNowPlayingData() {
        getCompositeDisposable().add(getDataManager().getNowPlaying(page)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new BaseObserver<Movie>() {
                    @Override
                    public void onResponseOK(List<Movie> movies) {
                        if (movies == null || movies.isEmpty()) {
                            getMvpView().endLoadMore();
                        } else {
                            getMvpView().hideLoadMore();
                            getMvpView().addMovies(movies);

                        }
                        page++;
                    }

                    @Override
                    public void onResponseError(String errorMessage) {
                        getMvpView().showFailedLoadMore();
                        getMvpView().showOkDialog(R.string.error, errorMessage);
                    }
                }));
    }
}
