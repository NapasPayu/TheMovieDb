package com.napas.themoviedb.data.network;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.napas.themoviedb.data.model.BaseResponse;
import com.napas.themoviedb.data.model.ErrorResponse;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {

    private static final String API_DEFAULT_ERROR = "Something wrong happened!";

    public abstract void onResponseOK(List<T> results);

    public abstract void onResponseError(String errorMessage);

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof ANError) {
            ANError anError = (ANError) throwable;
            handleApiError(anError);
        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        onResponseOK(baseResponse.getResults());
    }

    private void handleApiError(ANError error) {
        Gson gson = new Gson();
//        int errorCode = error.getErrorCode();
        String errorBody = error.getErrorBody();
//        String errorDetail = error.getErrorDetail();

        try {
            ErrorResponse errorResponse = gson.fromJson(errorBody, ErrorResponse.class);
            onResponseError(errorResponse.getStatusMessage());
        } catch (Exception e) {
            onResponseError(API_DEFAULT_ERROR);
        }
    }
}
