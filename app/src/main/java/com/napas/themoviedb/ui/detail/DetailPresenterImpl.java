package com.napas.themoviedb.ui.detail;

import com.napas.themoviedb.R;
import com.napas.themoviedb.data.DataManager;
import com.napas.themoviedb.data.model.Movie;
import com.napas.themoviedb.data.network.BaseObserver;
import com.napas.themoviedb.ui.base.BasePresenter;
import com.napas.themoviedb.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailPresenterImpl<V extends DetailView> extends BasePresenter<V> implements DetailPresenter<V> {

    @Inject
    public DetailPresenterImpl(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onInitSimilarMoviesData(Integer movieId) {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().getSimilarMovies(movieId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new BaseObserver<Movie>() {
                    @Override
                    public void onResponseOK(List<Movie> movies) {
                        getMvpView().hideLoading();
                        getMvpView().initSimilarMovies(movies);
                    }

                    @Override
                    public void onResponseError(String errorMessage) {
                        getMvpView().hideLoading();
                        getMvpView().showOkDialog(R.string.error, errorMessage);
                    }
                }));
    }
}
