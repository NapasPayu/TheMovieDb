package com.napas.themoviedb.data.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("status_message")
    private String statusMessage;


    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
