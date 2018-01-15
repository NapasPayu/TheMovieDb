package com.napas.themoviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse<T> {

    private List<T> results;
    @SerializedName("total_pages")
    private Integer totalPage;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
